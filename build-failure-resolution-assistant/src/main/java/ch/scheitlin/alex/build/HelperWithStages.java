package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.Build;

import java.util.Arrays;

public abstract class HelperWithStages {
    protected BuildFixHelperStage stage;

    public HelperWithStages() {
        this.stage = BuildFixHelperStage.NONE;
    }

    public boolean connect(String host, String username, String password) {
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.NONE,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.CONNECTED;

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

    public boolean download(Build build) {
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.CONNECTED,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.DOWNLOADED;

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

    abstract boolean downloadBuildLog(Build build);

    public boolean process() {
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.DOWNLOADED,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.PROCESSED;

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
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.PROCESSED,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.FIXING;

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
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.FIXING,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.CONNECTED;

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
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.CONNECTED,
                BuildFixHelperStage.FIXING,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.NONE;

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
        BuildFixHelperStage[] requiredStages = {
                BuildFixHelperStage.DOWNLOADED,
                BuildFixHelperStage.PROCESSED,
                BuildFixHelperStage.FIXING,
        };
        BuildFixHelperStage nextStage = BuildFixHelperStage.CONNECTED;

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

    private boolean stageCheck(BuildFixHelperStage[] stages) {
        return Arrays.asList(stages).contains(this.stage);
    }
}
