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
     * The modules of the maven build.
     */
    private List<MavenModule> modules;

    /**
     * Creates a new instance of a maven build.
     *
     * @param status     the build status of the maven build
     * @param failedGoal the failed goal of the maven build if the build failed (else {@code null}
     */
    public MavenBuild(MavenBuildStatus status, MavenGoal failedGoal) {
        this.status = status;
        this.failedGoal = failedGoal;
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
}
