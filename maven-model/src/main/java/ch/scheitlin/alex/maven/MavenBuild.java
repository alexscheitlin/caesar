package ch.scheitlin.alex.maven;

import java.util.List;

/**
 * Represents a maven build.
 */
public class MavenBuild {
    /**
     * The build status of the maven build.
     */
    private MavenBuildStatus status;

    /**
     * The failed goal of the maven build if the build failed.
     */
    private MavenGoal failedGoal;

    /**
     * <p>The error message if the build failed but no goal failed.</p>
     * <p>This might be the case if the build fails before any module is built (e.g. 'Could not resolve dependencies').
     * </p>
     */
    private String errorMessage;

    /**
     * The modules of the maven build.
     */
    private List<MavenModule> modules;

    /**
     * The build summary section at the end of the maven build log.
     */
    private List<String> buildSummary;

    /**
     * Creates a new instance of a maven build.
     *
     * @param status       the build status of the maven build
     * @param failedGoal   the failed goal of the maven build if the build failed (else {@code null}
     * @param errorMessage the error message if the build failed and no module was built yet (else {@code null}
     * @param buildSummary the build summary section at the end of the maven build log
     */
    public MavenBuild(MavenBuildStatus status, MavenGoal failedGoal, String errorMessage, List<String> buildSummary) {
        this.status = status;
        this.failedGoal = failedGoal;
        this.errorMessage = errorMessage;
        this.buildSummary = buildSummary;
    }

    /**
     * Gets the build status of the maven build.
     *
     * @return the build status of the maven build
     */
    public MavenBuildStatus getStatus() {
        return this.status;
    }

    /**
     * Gets the failed goal of the maven build.
     *
     * @return the failed goal of the maven build or {@code null} if the build did not fail
     */
    public MavenGoal getFailedGoal() {
        return this.failedGoal;
    }

    /**
     * Gets the error message of the maven build.
     *
     * @return the error message if the build failed but no goal failed or {@code null}
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Gets the modules of the maven build.
     *
     * @return the modules of the maven build
     */
    public List<MavenModule> getModules() {
        return this.modules;
    }

    /**
     * Sets the modules of the maven build.
     *
     * @param modules the modules of the maven build.
     */
    public void setModules(List<MavenModule> modules) {
        this.modules = modules;
    }

    /**
     * Gets the build summary section at the end of the maven build log.
     *
     * @return the build summary section at the end of the maven build log
     */
    public List<String> getBuildSummary() {
        return this.buildSummary;
    }
}
