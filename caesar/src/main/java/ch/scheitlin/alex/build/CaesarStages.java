package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServerBuild;

import java.util.Arrays;

public abstract class CaesarStages {
    protected BuildFixAssistantStage stage;

    public CaesarStages() {
        this.stage = BuildFixAssistantStage.NONE;
    }

    public void connect(String host, String username, String password) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.NONE,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.connectImpl(host, username, password);
        } catch (Exception ex) {
            throw new Exception("Could not connect to build server!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void connectImpl(String host, String username, String password) throws Exception;

    public void download(BuildServerBuild build) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.CONNECTED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.DOWNLOADED;

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.downloadImpl(build);
        } catch (Exception ex) {
            throw new Exception("Could not download build log!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void downloadImpl(BuildServerBuild build) throws Exception;

    public void process() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.DOWNLOADED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.PROCESSED;

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.processImpl();
        } catch (Exception ex) {
            throw new Exception("Could not process build log!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void processImpl() throws Exception;

    public void fix(String pathToLocalGitRepository) throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.PROCESSED,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.FIXING;

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.fixImpl(pathToLocalGitRepository);
        } catch (Exception ex) {
            throw new Exception("Could not initiate fixing!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void fixImpl(String pathToLocalGitRepository) throws Exception;

    public void finish() throws Exception {
        BuildFixAssistantStage[] requiredStages = {
                BuildFixAssistantStage.FIXING,
        };
        BuildFixAssistantStage nextStage = BuildFixAssistantStage.CONNECTED;

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.finishImpl();
        } catch (Exception ex) {
            throw new Exception("Could not finish fixing!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void finishImpl() throws Exception;

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
