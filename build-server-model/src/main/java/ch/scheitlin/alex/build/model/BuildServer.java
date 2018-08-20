package ch.scheitlin.alex.build.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Represents a build server with the following data:</p>
 * <ul>
 *     <li>projects that have multiple build configurations to run different builds</li>
 *     <li>build configurations to execute builds</li>
 *     <li>branches of the version control system by which the builds can be grouped</li>
 *     <li>builds that contain information about executed builds</li>
 * </ul>
 */
public class BuildServer {
    /**
     * The type of the build server.
     */
    private BuildServerType type;

    /**
     * The projects configured on the build server.
     */
    private List<BuildServerProject> projects;

    /**
     * Creates a new instance of a build server.
     *
     * @param type     the type of the build server
     * @param projects the projects configured on the build server
     */
    public BuildServer(BuildServerType type, List<BuildServerProject> projects) {
        this.type = type;
        this.projects = projects;
    }

    /**
     * Gets the type of the build server.
     *
     * @return the type of the build server.
     */
    public BuildServerType getType() {
        return this.type;
    }

    /**
     * Gets the projects of the build server.
     *
     * @return the projects of the build server
     */
    public List<BuildServerProject> getProjects() {
        return this.projects;
    }

    /**
     * Gets a specific project of the build server by its name.
     *
     * @param projectName the name of the project to retrieve
     * @return the specified project of the build server or {@code null} if no project matches the provided name
     */
    public BuildServerProject getProject(String projectName) {
        for (BuildServerProject project : this.projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }

        return null;
    }

    /**
     * Gets the names of all projects of the build server.
     *
     * @return the names of all projects of the build server
     */
    public List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<String>();

        for (BuildServerProject project : this.projects) {
            projectNames.add(project.getName());
        }

        return projectNames;
    }
}
