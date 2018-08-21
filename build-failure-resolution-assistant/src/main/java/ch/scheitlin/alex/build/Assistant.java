package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.*;
import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.git.api.GitApi;
import ch.scheitlin.alex.maven.Classifier;
import ch.scheitlin.alex.maven.MavenBuildLogParser;
import ch.scheitlin.alex.maven.MavenGoalLogParser;
import ch.scheitlin.alex.maven.MavenBuild;
import ch.scheitlin.alex.teamcity.api.TeamcityApi;
import javafx.util.Pair;

import java.util.List;

public class Assistant extends AssistantWithStages {
    private BuildServerType buildServerType;

    // -----------------------------------------------------
    // variables to set in stages
    // -----------------------------------------------------
    // connect
    private BuildServerApi buildServerApi;

    // download
    private BuildServerBuild buildServerBuild;
    private String rawTeamCityBuildLog;

    // process
    private Build build;
    private String rawMavenBuildLog;
    public MavenBuild mavenBuild;
    public String failureCategory;
    public List<Error> errors;

    // fix
    private GitApi gitApi;
    private String gitRepositoryOriginUrl;
    private String previousBranch;
    private String stashedChanges;

    public Assistant(BuildServerType buildServerType) {
        this.buildServerType = buildServerType;
    }

    // -------------------
    // Stages

    public boolean connectToBuildServer(String host, String username, String password) {
        // return false if connection test fails
        if (!this.testTeamCityConnection(host, username, password)) {
            return false;
        }

        // login to api
        this.buildServerApi = new BuildServerApi(this.buildServerType);
        this.buildServerApi.login(host, username, password);

        return true;
    }

    public boolean downloadBuildLog(BuildServerBuild build) {
        this.buildServerBuild = build;

        try {
            this.rawTeamCityBuildLog = this.buildServerApi.downloadBuildLog(this.buildServerBuild.getId());
        } catch (Exception ex) {
            //throw new Exception("Build log could not be downloaded.");
            return false;
        }

        if (this.rawTeamCityBuildLog == null || this.rawTeamCityBuildLog.equals("")) {
            //throw new Exception("Build log could not be processed.");
            return false;
        }

        return true;
    }

    public boolean processBuildLog() {
        // parse build server build log
        this.build = null;
        try {
            BuildServerBuildLogParser buildLogParser = new BuildServerBuildLogParser(this.buildServerType);
            this.build = buildLogParser.parseBuildLog(this.rawTeamCityBuildLog);
        } catch (Exception ex) {
            //throw new Exception("TeamCity build log could not be parsed.");
            return false;
        }

        // extract maven log
        this.rawMavenBuildLog = build.getMavenLog();
        if (this.rawMavenBuildLog == null) {
            //throw new Exception("No Maven build log found.");
            return false;
        }

        // parse maven log
        this.mavenBuild = MavenBuildLogParser.parse(this.rawMavenBuildLog);

        // classify error message or failed maven goal
        if (mavenBuild.getErrorMessage() != null) {
            String errorMessage = mavenBuild.getErrorMessage();

            try {
                Classifier classifier = new Classifier();
                this.failureCategory = classifier.classify(errorMessage).toString();
            } catch (Exception e) {
                return false;
            }

            // parse log of error message (from the build summary)
            List<String> errorMessageLogLines = mavenBuild.getBuildSummary();
            String errorMessageLog = "";
            for (String line : errorMessageLogLines) {
                errorMessageLog += (line + "\n");
            }

            this.errors = MavenGoalLogParser.parseErrorLog(errorMessage, errorMessageLog);

        } else if (mavenBuild.getFailedGoal() != null) {
            String failedGoal = mavenBuild.getFailedGoal().getPlugin().getName() + ":" + mavenBuild.getFailedGoal().getName();
            try {
                Classifier classifier = new Classifier();
                this.failureCategory = classifier.classify(failedGoal).toString();
            } catch (Exception e) {
                //throw new Exception("Could not read goals from configuration.");
                return false;
            }

            // parse log of failed maven goal
            List<String> failedGoalLogLines = mavenBuild.getFailedGoal().getLines();
            String failedGoalLog = "";
            for (String line : failedGoalLogLines) {
                failedGoalLog += (line + "\n");
            }

            this.errors = MavenGoalLogParser.parseGoalLog(
                    mavenBuild.getFailedGoal().getPlugin().getName(),
                    mavenBuild.getFailedGoal().getName(),
                    failedGoalLog
            );
        }

        return true;
    }

    public boolean startFixingBrokenBuild(String pathToLocalGitRepository) {
        String urlToRemoteGitRepository = this.buildServerBuild.getRepository();
        String commitId = this.buildServerBuild.getCommit();
        String buildNumber = this.buildServerBuild.getNumber();

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

                // fetch from remote repository
                final String USERNAME = "jgit-workflow";
                final String PASSWORD = "220d90c85130348b8e2844a2bacaab7f5f95fc56";
                try {
                    this.gitApi.fetchFromRemoteRepository(USERNAME, PASSWORD);
                } catch (Exception ex) {
                    return false;
                }

                // checkout to commit to fix
                // branch name: fix-buildNumber (eg. fix-51)
                // if branch already exists append version (eg. fix-51-v2)
                String branchName = null;
                int counter = 0;
                int maxTries = 100;
                while (counter++ < maxTries) {
                    branchName = "fix-" + buildNumber;

                    if (counter != 1) {
                        branchName += "-v" + counter;
                    }
                    try {
                        this.gitApi.createBranchFromCommit(commitToFix, branchName);
                        break;
                    } catch (Exception e) {
                        if (counter == maxTries) {
                            System.out.println("Could not create new branch!");
                            return false;
                        }
                        continue;
                    }
                }

                System.out.println("Created new branch '" + branchName + "' starting at commit '" + commitToFix + "'.");
                System.out.println("On branch: " + this.gitApi.getCurrentBranch());
                System.out.println();

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
        this.buildServerApi = null;

        return true;
    }

    public boolean abortStage() {
        // clean variables

        // download
        this.buildServerBuild = null;
        this.rawTeamCityBuildLog = null;

        // process
        this.build = null;
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

    public BuildServer getBuildServerInformation() {
        return this.buildServerApi.toBuildServerModel();
    }

    public boolean testTeamCityConnection(String host, String username, String password) {
        return new TeamcityApi().testConnection(host, username, password);
    }

    public boolean isInNoStage() {
        return super.stage == BuildFixAssistantStage.NONE;
    }

    public boolean isConnected() {
        return super.stage == BuildFixAssistantStage.CONNECTED;
    }

    public boolean hasDownloaded() {
        return super.stage == BuildFixAssistantStage.DOWNLOADED;
    }

    public boolean hasProcessed() {
        return super.stage == BuildFixAssistantStage.PROCESSED;
    }

    public boolean isFixing() {
        return super.stage == BuildFixAssistantStage.FIXING;
    }

    public String getGitRepositoryOriginUrl() {
        return this.gitRepositoryOriginUrl;
    }

    // download
    public String getRawTeamCityBuildLog() {
        return this.rawTeamCityBuildLog;
    }
}
