package ch.scheitlin.alex.maven;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a module of a maven build.
 */
public class MavenModule {
    /**
     * The name of the maven module.
     */
    private String name;

    /**
     * The version of the maven module.
     */
    private String version;

    /**
     * The build status of the maven module.
     */
    private MavenModuleStatus status;

    /**
     * The duration of the build of the maven module.
     */
    private String duration;

    /**
     * The executed goals in the build of the maven module.
     */
    private List<MavenGoal> goals;

    /**
     * Creates a new instance of a maven module.
     *
     * @param name the name of the maven module
     */
    public MavenModule(String name) {
        this.name = name;
        this.goals = new ArrayList<MavenGoal>();
    }

    /**
     * Gets the name of the maven module.
     *
     * @return the name of the maven module
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the version of the maven module.
     *
     * @return the version of the maven module
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version of the maven module.
     *
     * @param version the version of the maven module
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the build status of the maven module.
     *
     * @return the build status of the maven module
     */
    public MavenModuleStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the build status of the maven module.
     *
     * @param status the build status of the maven module
     */
    public void setStatus(MavenModuleStatus status) {
        this.status = status;
    }

    /**
     * Gets the duration of the build of the maven module.
     *
     * @return the duration of the build of the maven module
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Sets the duration of the build of the maven module.
     *
     * @param duration the duration of the build of the maven module
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the executed goals in the build of the maven module.
     *
     * @return the executed goals in the build of the maven module
     */
    public List<MavenGoal> getGoals() {
        return this.goals;
    }

    /**
     * Sets the executed goals in the build of the maven module.
     *
     * @param goals the executed goals in the build of the maven module
     */
    public void setGoals(List<MavenGoal> goals) {
        this.goals = goals;
    }

    /**
     * Adds an executed goal in the build of the maven module.
     *
     * @param goal an executed goal in the build of the maven module
     */
    public void addGoal(MavenGoal goal) {
        this.goals.add(goal);
    }
}
