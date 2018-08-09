package ch.scheitlin.alex.build.model;

import java.util.List;

/**
 * <p>Represents a build configuration of a build server with the following data:</p>
 * <ul>
 * <li>branches of the version control system by which the builds can be grouped</li>
 * <li>builds that contain information about executed builds</li>
 * </ul>
 */
public class BuildConfiguration {
    /**
     * The name of the build configuration.
     */
    private String name;

    /**
     * The builds executed with the build configuration grouped by their version control system branch.
     */
    private List<Branch> branches;

    /**
     * Creates a new instance of a build configuration.
     *
     * @param name     the name of the build configuration
     * @param branches builds executed with the build configuration grouped by their version control system branch
     */
    public BuildConfiguration(String name, List<Branch> branches) {
        this.name = name;
        this.branches = branches;
    }

    /**
     * Gets the name of the build configuration.
     *
     * @return the name of the build configuration
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the branches with the builds of the build configuration.
     *
     * @return the branches with the builds of the build configuration
     */
    public List<Branch> getBranches() {
        return this.branches;
    }
}
