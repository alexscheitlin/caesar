package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServerBuild;

import java.util.Arrays;

public abstract class CaesarStages {
    private CaesarStage stage;

    public CaesarStages() {
        this.stage = CaesarStage.NONE;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // connect
    // -----------------------------------------------------------------------------------------------------------------

    public void connect(String host, String username, String password) throws Exception {
        CaesarStage nextStage = CaesarStage.CONNECTED;
        CaesarStage[] requiredStages = {
                CaesarStage.NONE,
        };

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

    // -----------------------------------------------------------------------------------------------------------------
    // download
    // -----------------------------------------------------------------------------------------------------------------

    public void download(BuildServerBuild build) throws Exception {
        CaesarStage nextStage = CaesarStage.DOWNLOADED;
        CaesarStage[] requiredStages = {
                CaesarStage.CONNECTED,
        };

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

    // -----------------------------------------------------------------------------------------------------------------
    // process
    // -----------------------------------------------------------------------------------------------------------------

    public void process() throws Exception {
        CaesarStage nextStage = CaesarStage.PROCESSED;
        CaesarStage[] requiredStages = {
                CaesarStage.DOWNLOADED,
        };

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

    // -----------------------------------------------------------------------------------------------------------------
    // fix
    // -----------------------------------------------------------------------------------------------------------------

    public void fix(String pathToLocalGitRepository) throws Exception {
        CaesarStage nextStage = CaesarStage.FIXING;
        CaesarStage[] requiredStages = {
                CaesarStage.PROCESSED,
        };

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

    // -----------------------------------------------------------------------------------------------------------------
    // finish
    // -----------------------------------------------------------------------------------------------------------------

    public void finish() throws Exception {
        CaesarStage nextStage = CaesarStage.CONNECTED;
        CaesarStage[] requiredStages = {
                CaesarStage.FIXING,
        };

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

    // -----------------------------------------------------------------------------------------------------------------
    // disconnect
    // -----------------------------------------------------------------------------------------------------------------

    public void disconnect() throws Exception {
        CaesarStage nextStage = CaesarStage.NONE;
        CaesarStage[] requiredStages = {
                CaesarStage.CONNECTED,
                CaesarStage.FIXING,
        };

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.disconnectImpl();
        } catch (Exception ex) {
            throw new Exception("Could not disconnect from build server!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void disconnectImpl() throws Exception;

    // -----------------------------------------------------------------------------------------------------------------
    // abort
    // -----------------------------------------------------------------------------------------------------------------

    public void abort() throws Exception {
        CaesarStage nextStage = CaesarStage.CONNECTED;
        CaesarStage[] requiredStages = {
                CaesarStage.DOWNLOADED,
                CaesarStage.PROCESSED,
                CaesarStage.FIXING,
        };

        try {
            // check whether caesar is in the required stage
            stageCheck(requiredStages);

            // try to enter next stage
            this.abortImpl();
        } catch (Exception ex) {
            throw new Exception("Could not abort!\n" + ex.getMessage());
        }

        // update the stage
        this.stage = nextStage;
    }

    abstract void abortImpl() throws Exception;

    // -----------------------------------------------------------------------------------------------------------------
    // stages
    // -----------------------------------------------------------------------------------------------------------------

    public boolean isInNoStage() {
        return this.stage == CaesarStage.NONE;
    }

    public boolean isConnected() {
        return this.stage == CaesarStage.CONNECTED;
    }

    public boolean hasDownloaded() {
        return this.stage == CaesarStage.DOWNLOADED;
    }

    public boolean hasProcessed() {
        return this.stage == CaesarStage.PROCESSED;
    }

    public boolean isFixing() {
        return this.stage == CaesarStage.FIXING;
    }

    private void stageCheck(CaesarStage[] stages) throws Exception {
        if (!Arrays.asList(stages).contains(this.stage)) {
            throw new Exception("CAESAR is in stage '" + this.stage + "' but needs to be in one of the following " +
                    "stages: " + Arrays.toString(stages));
        }
    }
}
