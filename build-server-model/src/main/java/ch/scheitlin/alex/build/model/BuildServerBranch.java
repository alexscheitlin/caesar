package ch.scheitlin.alex.build.model;

import java.util.List;

/**
 * Represents builds of a build server having the same branch of the version control system.
 */
public class BuildServerBranch {
    /**
     * The name of the branch.
     */
    private String name;

    /**
     * The builds of the branch.
     */
    private List<BuildServerBuild> builds;

    /**
     * Creates a new instance of a branch.
     *
     * @param name   the name of the branch
     * @param builds the builds of the branch
     */
    public BuildServerBranch(String name, List<BuildServerBuild> builds) {
        this.name = name;
        this.builds = builds;
    }

    /**
     * Gets the name of the branch.
     *
     * @return the name of the branch
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the builds of the branch.
     *
     * @return the builds of the branch
     */
    public List<BuildServerBuild> getBuilds() {
        return this.builds;
    }
}
