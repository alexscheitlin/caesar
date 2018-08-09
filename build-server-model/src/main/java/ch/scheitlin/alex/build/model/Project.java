package ch.scheitlin.alex.build.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Represents a project of a build server with the following data:</p>
 * <ul>
 *     <li>build configurations to execute builds</li>
 *     <li>branches of the version control system by which the builds can be grouped</li>
 *     <li>builds that contain information about executed builds</li>
 * </ul>
 */
public class Project {
    /**
     * The name of the project.
     */
    private String name;

    /**
     * The build configurations of the project.
     */
    private List<BuildConfiguration> buildConfigurations;

    /**
     * Creates a new instance of a project.
     *
     * @param name                the name of the project
     * @param buildConfigurations the build configurations of the project
     */
    public Project(String name, List<BuildConfiguration> buildConfigurations) {
        this.name = name;
        this.buildConfigurations = buildConfigurations;
    }

    /**
     * Gets the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the build configurations of the project.
     *
     * @return the build configurations of the project
     */
    public List<BuildConfiguration> getBuildConfigurations() {
        return this.buildConfigurations;
    }

    /**
     * Gets the names of all build configuration of the project.
     *
     * @return the names of all build configurations of the project
     */
    public List<String> getBuildConfigurationNames() {
        List<String> buildConfigurationNames = new ArrayList<String>();

        for (BuildConfiguration buildConfiguration : this.buildConfigurations) {
            buildConfigurationNames.add(buildConfiguration.getName());
        }

        return buildConfigurationNames;
    }
}
