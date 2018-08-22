package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServerBuild;

import java.util.Arrays;

public abstract class AssistantWithStages {
    protected BuildFixAssistantStage stage;

    public AssistantWithStages() {
        this.stage = BuildFixAssistantStage.NONE;
    }

    public boolean connect(String host, String username, String password) {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.NONE,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.connectToBuildServer(host, username, password)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean connectToBuildServer(String host, String username, String password);

    public boolean download(BuildServerBuild build) {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.CONNECTED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.DOWNLOADED;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.downloadBuildLog(build)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean downloadBuildLog(BuildServerBuild build);

    public boolean process() {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.DOWNLOADED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.PROCESSED;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.processBuildLog()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean processBuildLog();

    public boolean fix(String pathToLocalGitRepository) {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.PROCESSED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.FIXING;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.startFixingBrokenBuild(pathToLocalGitRepository)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean startFixingBrokenBuild(String pathToLocalGitRepository);

    public boolean finish() {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.stopFixingBrokenBuild()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean stopFixingBrokenBuild();

    public boolean disconnect() {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.CONNECTED,
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.NONE;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.disconnectFromBuildServer()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean disconnectFromBuildServer();

    public boolean abort() {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.DOWNLOADED,
                BuildFixAssistantStage.PROCESSED,
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        if (!stageCheck(requiredStages)) {
            return false;
        }

        // try to enter next stage
        if (!this.abortStage()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean abortStage();

    private boolean stageCheck(BuildFixAssistantStage[] stages) {
        return Arrays.asList(stages).contains(this.stage);
    }
}
