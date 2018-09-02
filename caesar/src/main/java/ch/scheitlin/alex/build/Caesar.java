package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.*;
import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.git.api.GitApi;
import ch.scheitlin.alex.maven.Classifier;
import ch.scheitlin.alex.maven.MavenBuildLogParser;
import ch.scheitlin.alex.maven.MavenGoalLogParser;
import ch.scheitlin.alex.maven.MavenBuild;
import javafx.util.Pair;

import java.util.List;

public class Caesar extends CaesarStages {
    private BuildServerType buildServerType;

    public Caesar(BuildServerType buildServerType) {
        this.buildServerType = buildServerType;
    }

    public static boolean testBuildServerConnection(BuildServerType buildServerType, String host, String username, String password) {
        return new BuildServerApi(buildServerType).testConnection(host, username, password);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // connect
    // -----------------------------------------------------------------------------------------------------------------

    // internal concern
    private BuildServerApi buildServerApi;

    // internal and external concern
    private BuildServer buildServerModel;

    void connectImpl(String host, String username, String password) throws Exception {
        // check connection to build server
        if (!testBuildServerConnection(this.buildServerType, host, username, password)) {
            throw new Exception("Connection test failed!");
        }

        // login to api
        this.buildServerApi = new BuildServerApi(this.buildServerType);
        this.buildServerApi.login(host, username, password);

        // get build server model
        this.fetchBuildServerInformation();
    }

    public BuildServer fetchBuildServerInformation() {
        this.buildServerModel = this.buildServerApi.toBuildServerModel();
        return this.buildServerModel;
    }

    public BuildServer getBuildServerInformation() {
        return this.buildServerModel;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // download
    // -----------------------------------------------------------------------------------------------------------------

    // internal concern
    private BuildServerBuild buildServerBuild;

    // internal and external concern
    private String buildServerBuildLog;

    void downloadImpl(BuildServerBuild build) throws Exception {
        this.buildServerBuild = build;

        try {
            this.buildServerBuildLog = this.buildServerApi.downloadBuildLog(this.buildServerBuild.getId());
        } catch (Exception ex) {
            throw new Exception("Download failed!");
        }

        if (this.buildServerBuildLog == null || this.buildServerBuildLog.equals("")) {
            throw new Exception("Downloaded build log is empty!");
        }
    }

    public String getBuildServerBuildLog() {
        return this.buildServerBuildLog;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // process
    // -----------------------------------------------------------------------------------------------------------------

    // internal and external concern
    private String mavenBuildLog;
    private MavenBuild mavenBuild;
    private String failureCategory;
    private List<Error> errors;

    void processImpl() throws Exception {
        // parse build server build log
        Build build = null;
        try {
            BuildServerBuildLogParser buildLogParser = new BuildServerBuildLogParser(this.buildServerType);
            build = buildLogParser.parseBuildLog(this.buildServerBuildLog);
        } catch (Exception ex) {
            throw new Exception("Parsing of the build server build log failed!");
        }

        // extract maven log
        this.mavenBuildLog = build.getMavenLog();
        if (this.mavenBuildLog == null) {
            throw new Exception("Retrieved maven build log is empty!");
        }

        // parse maven log
        this.mavenBuild = MavenBuildLogParser.parse(this.mavenBuildLog);

        // classify error message or failed maven goal
        if (mavenBuild.getErrorMessage() != null) {
            // classify error message
            String errorMessage = mavenBuild.getErrorMessage();

            try {
                Classifier classifier = new Classifier();
                this.failureCategory = classifier.classify(errorMessage).toString();
            } catch (Exception e) {
                throw new Exception("Failure classification failed!");
            }

            // parse log of error message (from the build summary)
            List<String> errorMessageLogLines = mavenBuild.getBuildSummary();
            String errorMessageLog = "";
            for (String line : errorMessageLogLines) {
                errorMessageLog += (line + "\n");
            }

            this.errors = MavenGoalLogParser.parseErrorLog(errorMessage, errorMessageLog);

        } else if (mavenBuild.getFailedGoal() != null) {
            // classify failed goal
            String failedGoal = mavenBuild.getFailedGoal().getPlugin().getName() + ":" + mavenBuild.getFailedGoal().getName();
            try {
                Classifier classifier = new Classifier();
                this.failureCategory = classifier.classify(failedGoal).toString();
            } catch (Exception e) {
                throw new Exception("Failure classification failed!");
            }

            // parse log of failed maven goal
            List<String> failedGoalLogLines = mavenBuild.getFailedGoal().getLines();
            failedGoalLogLines.addAll(mavenBuild.getBuildSummary());
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
    }

    public String getMavenBuildLog() {
        return this.mavenBuildLog;
    }

    public MavenBuild getMavenBuild() {
        return this.mavenBuild;
    }

    public String getFailureCategory() {
        return this.failureCategory;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // fix
    // -----------------------------------------------------------------------------------------------------------------

    // internal concern
    private GitApi gitApi;
    private String previousBranch;

    // internal and external concern
    private String gitRepositoryUrl;
    private String stashedChanges;
    private String newBranch;

    void fixImpl(String pathToLocalGitRepository) throws Exception {
        String urlToRemoteGitRepository = this.buildServerBuild.getRepository();
        String commitId = this.buildServerBuild.getCommit();
        String buildNumber = this.buildServerBuild.getNumber();

        // connect to git repository
        try {
            // connect to git repository
            this.gitApi = new GitApi(pathToLocalGitRepository);

            this.previousBranch = this.gitApi.getCurrentBranch();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            throw new Exception("Connection to local git repository failed!");
        }

        // get repository urls
        List<Pair<String, String>> remotes = this.gitApi.getRemoteUrls();
        if (remotes == null || remotes.size() == 0) {
            throw new Exception("No remote repositories defined in local git repository!");
        }

        // check whether the local git repository and the one used on the build server have the same remote repository
        for (Pair<String, String> remote : remotes) {
            if (remote.getValue().equals(urlToRemoteGitRepository)) {
                this.gitRepositoryUrl = remote.getValue();
            }
        }
        if (this.gitRepositoryUrl == null) {
            throw new Exception("Local remote repository does not match the one used by the build server!");
        }

        // stash uncommitted changes if there are any
        try {
            if (!this.gitApi.isRepositoryClean()) {
                this.gitApi.stageAllFiles();
                this.stashedChanges = this.gitApi.stashTrackedAndStagedFiles();
            }
        } catch (Exception ex) {
            throw new Exception("Stashing open changes failed!");
        }

        // fetch latest changes from remote repository
        // disabled as currently not needed
        // username and password should not be set within CAESAR
        /*
        final String USERNAME = "jgit-workflow";
        final String PASSWORD = "220d90c85130348b8e2844a2bacaab7f5f95fc56";
        try {
            this.gitApi.fetchFromRemoteRepository(USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return false;
        }
        */

        // checkout to the specified commit
        // the branch name is named as follows: fix-buildNumber (eg. fix-51)
        // if the branch already exists append version (eg. fix-51-v2)
        String branchName = null;
        int counter = 0;
        int maxTries = 100;
        while (counter++ < maxTries) {
            branchName = "fix-" + buildNumber;

            if (counter != 1) {
                branchName += "-v" + counter;
            }
            try {
                this.gitApi.createBranchFromCommit(commitId, branchName);
                this.newBranch = branchName;
                break;
            } catch (Exception e) {
                if (counter == maxTries) {
                    throw new Exception("Creating new branch failed!");
                }
            }
        }
    }

    public String getGitRepositoryUrl() {
        return this.gitRepositoryUrl;
    }

    public String getStashedChanges() {
        return this.stashedChanges;
    }

    public String getNewBranch() {
        return this.newBranch;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // finish
    // -----------------------------------------------------------------------------------------------------------------

    void finishImpl() {
        // let the user go back to the previous branch
        /*
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
            return false;
        }
        */

        this.abortImpl();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // disconnect
    // -----------------------------------------------------------------------------------------------------------------

    void disconnectImpl() {
        // clean connect variables
        this.buildServerApi = null;
        this.buildServerModel = null;

        this.abortImpl();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // abort
    // -----------------------------------------------------------------------------------------------------------------

    void abortImpl() {
        // clean download variables
        this.buildServerBuild = null;
        this.buildServerBuildLog = null;

        // clean process variables
        this.mavenBuildLog = null;
        this.mavenBuild = null;
        this.failureCategory = null;
        this.errors = null;

        // clean fix variables
        this.gitApi = null;
        this.gitRepositoryUrl = null;
        this.previousBranch = null;
        this.stashedChanges = null;
        this.newBranch = null;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // stages
    // -----------------------------------------------------------------------------------------------------------------

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
}
