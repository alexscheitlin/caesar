package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServerBuild;

import java.util.Arrays;

public abstract class CaesarStages {
    protected BuildFixAssistantStage stage;

    public CaesarStages() {
        this.stage = BuildFixAssistantStage.NONE;
    }

    public boolean connect(String host, String username, String password) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.NONE,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not connect to build server!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.connectImpl(host, username, password)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean connectImpl(String host, String username, String password);

    public boolean download(BuildServerBuild build) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.CONNECTED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.DOWNLOADED;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not download build log!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.downloadImpl(build)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean downloadImpl(BuildServerBuild build);

    public boolean process() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.DOWNLOADED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.PROCESSED;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not process build log!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.processImpl()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean processImpl();

    public boolean fix(String pathToLocalGitRepository) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.PROCESSED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.FIXING;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not initiate fixing!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.fixImpl(pathToLocalGitRepository)) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean fixImpl(String pathToLocalGitRepository);

    public boolean finish() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not finish fixing!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.finishImpl()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean finishImpl();

    public boolean disconnect() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.CONNECTED,
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.NONE;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not disconnect from build server!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.disconnectImpl()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean disconnectImpl();

    public boolean abort() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.DOWNLOADED,
                BuildFixAssistantStage.PROCESSED,
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        // check whether this helper instance is in the required stage
        try {
            stageCheck(requiredStages);
        } catch (Exception ex) {
            throw new Exception("Could not abort!\n" + ex.getMessage());
        }

        // try to enter next stage
        if (!this.abortImpl()) {
            return false;
        }

        // update the stage of this helper instance
        this.stage = nextStage;

        return true;
    }

    abstract boolean abortImpl();

    private void stageCheck(BuildFixAssistantStage[] stages) throws Exception {
        if (!Arrays.asList(stages).contains(this.stage)) {
            throw new Exception("CAESAR is in stage '" + this.stage + "' but needs to be in one of the following " +
                    "stages: " + Arrays.toString(stages));
        }
    }
}
