package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.Build;
import ch.scheitlin.alex.build.model.BuildConfiguration;
import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.git.api.GitApi;
import ch.scheitlin.alex.maven.Classifier;
import ch.scheitlin.alex.maven.MavenBuildLogParser;
import ch.scheitlin.alex.maven.MavenGoalLogParser;
import ch.scheitlin.alex.maven.MavenBuild;
import ch.scheitlin.alex.teamcity.BuildLogParser;
import ch.scheitlin.alex.teamcity.api.TeamcityApi;
import ch.scheitlin.alex.teamcity.TeamCityBuild;
import javafx.util.Pair;
import org.jetbrains.teamcity.rest.Project;

import java.util.ArrayList;
import java.util.List;

public class Helper extends HelperWithStages {
    private List<Project> projects;


    // -----------------------------------------------------
    // variables to set in stages
    // -----------------------------------------------------
    // connect
    private TeamcityApi teamCityApi;

    // download
    private Build build;
    private String rawTeamCityBuildLog;

    // process
    private TeamCityBuild teamCityBuild;
    private String rawMavenBuildLog;
    public MavenBuild mavenBuild;
    public String failureCategory;
    public List<Error> errors;

    // fix
    private GitApi gitApi;
    private String gitRepositoryOriginUrl;
    private String previousBranch;
    private String stashedChanges;

    public boolean connectToBuildServer(String host, String username, String password) {
        // return false if connection test fails
        if (!this.testTeamCityConnection(host, username, password)) {
            return false;
        }

        // store team city instance
        this.teamCityApi = new TeamcityApi(host, username, password);

        return true;
    }

    public boolean downloadBuildLog(Build build) {
        this.build = build;

        try {
            this.rawTeamCityBuildLog = this.teamCityApi.getBuildLog(this.build.getId());
        } catch (Exception ex) {
            //throw new Exception("TeamCity build log could not be downloaded.");
            return false;
        }

        if (this.rawTeamCityBuildLog == null || this.rawTeamCityBuildLog.equals("")) {
            //throw new Exception("TeamCity build log could not be processed.");
            return false;
        }

        return true;
    }

    public boolean processBuildLog() {
        // parse teamcity build log
        this.teamCityBuild = null;
        try {
            teamCityBuild = BuildLogParser.parseBuildLog(this.rawTeamCityBuildLog);
        } catch (Exception ex) {
            //throw new Exception("TeamCity build log could not be parsed.");
            return false;
        }

        // extract maven log
        this.rawMavenBuildLog = teamCityBuild.getMavenOutput();
        if (this.rawMavenBuildLog == null) {
            //throw new Exception("No Maven build log found.");
            return false;
        }

        // parse maven log
        this.mavenBuild = MavenBuildLogParser.parse(this.rawMavenBuildLog);

        // classify failed maven goal
        if (mavenBuild.failedGoal != null) {
            String failedGoal = mavenBuild.failedGoal.plugin + ":" + mavenBuild.failedGoal.name;
            try {
                Classifier classifier = new Classifier();
                this.failureCategory = classifier.classify(failedGoal).toString();
            } catch (Exception e) {
                //throw new Exception("Could not read goals from configuration.");
                return false;
            }

            // parse log of failed maven goal
            List<String> failedGoalLogLines = mavenBuild.failedGoal.lines;
            String failedGoalLog = "";
            for (String line : failedGoalLogLines) {
                failedGoalLog += (line + "\n");
            }

            this.errors = MavenGoalLogParser.parseGoalLog(mavenBuild.failedGoal.plugin, mavenBuild.failedGoal.name, failedGoalLog);
        }

        return true;
    }

    public boolean startFixingBrokenBuild(String pathToLocalGitRepository) {
        String urlToRemoteGitRepository = this.build.getRepository();
        String commitId = this.build.getCommit();
        String buildNumber = this.build.getNumber();

        // connect to git and get git origin remote url
        try {
            // connect to git repository
            this.gitApi = new GitApi(pathToLocalGitRepository);

            // get repository origin url
            List<Pair<String, String>> remotes = this.gitApi.getRemoteUrls();
            for (Pair<String, String> remote : remotes) {
                if (remote.getKey().equals("origin")) {
                    this.gitRepositoryOriginUrl = remote.getValue();
                }
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return false;
        }

        // check whether the local git repository and the teamcity build have the same repository as origin
        if (!this.gitRepositoryOriginUrl.equals(urlToRemoteGitRepository)) {
            /*
            throw new Exception("Different repository origin detected:\n" +
                    "\tLocal Repository: " + this.gitRepositoryOriginUrl + "\n" +
                    "\tBuild Repository: " + urlToRemoteGitRepository
            );*/
            return false;
        }

        try {
            // ***********************
            // git workflow
            try {
                String commitToFix = commitId;
                String previousBranch = this.gitApi.getCurrentBranch();
                String stashedChanges = null;

                System.out.println("On branch: " + previousBranch);

                // stash uncommitted changes if there are any
                if (!this.gitApi.isRepositoryClean()) {
                    this.gitApi.stageAllFiles();
                    stashedChanges = this.gitApi.stashTrackedAndStagedFiles();
                    System.out.println("Open changes are stashed!");
                } else {
                    System.out.println("No open changes detected.");
                }
                System.out.println();

                // checkout to commit to fix
                String branchName = "fix-" + buildNumber;
                int counter = 0;
                while (counter++ < 10) {
                    if (counter != 1) {
                        branchName += "-" + counter;
                    }
                    try {
                        this.gitApi.createBranchFromCommit(commitToFix, branchName);
                        break;
                    } catch (Exception e) {
                        continue;
                    }
                }
                System.out.println("Created new branch '" + branchName + "' starting at commit '" + commitToFix + "'.");

                this.previousBranch = previousBranch;
                this.stashedChanges = stashedChanges;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    public boolean stopFixingBrokenBuild() {
        try {
            // checkout branch
            this.gitApi.checkoutBranch(this.previousBranch);
            System.out.println("On branch: " + this.gitApi.getCurrentBranch());

            // print whether stashed before or not
            if (this.stashedChanges != null) {
                System.out.println("Stashed changes detected!");

                if (!this.gitApi.isRepositoryClean()) {
                    System.out.println("Open changes detected!");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        this.previousBranch = null;
        this.stashedChanges = null;

        return true;
    }

    public boolean disconnectFromBuildServer() {
        // clean variables
        this.teamCityApi = null;

        return true;
    }

    public boolean abortStage() {
        // clean variables

        // download
        this.build = null;
        this.rawTeamCityBuildLog = null;

        // process
        this.teamCityBuild = null;
        this.rawMavenBuildLog = null;
        this.mavenBuild = null;
        this.failureCategory = null;
        this.errors = null;

        // fix
        this.gitApi = null;
        this.gitRepositoryOriginUrl = null;
        this.previousBranch = null;
        this.stashedChanges = null;

        return true;
    }

    // -------------------


    public List<String> getTeamCityProjectNames() {
        this.projects = this.teamCityApi.getProjects();

        List<String> projectNames = new ArrayList<String>();
        for (Project project : this.projects) {
            projectNames.add(project.getName());
        }

        return projectNames;
    }

    public List<BuildConfiguration> getBuildConfigurationsToShow(String projectName) {
        for (Project project : this.projects) {
            if (project.getName().equals(projectName)) {
                return this.teamCityApi.getBuildConfigurationsToShow(project);
            }
        }

        return null;
    }

    public boolean testTeamCityConnection(String host, String username, String password) {
        return TeamcityApi.testTeamCityConnection(host, username, password);
    }

    public boolean isInNoStage() {
        return super.stage == BuildFixHelperStage.NONE;
    }

    public boolean isConnected() {
        return super.stage == BuildFixHelperStage.CONNECTED;
    }

    public boolean hasDownloaded() {
        return super.stage == BuildFixHelperStage.DOWNLOADED;
    }

    public boolean hasProcessed() {
        return super.stage == BuildFixHelperStage.PROCESSED;
    }

    public boolean isFixing() {
        return super.stage == BuildFixHelperStage.FIXING;
    }

    public String getGitRepositoryOriginUrl() {
        return this.gitRepositoryOriginUrl;
    }
}
