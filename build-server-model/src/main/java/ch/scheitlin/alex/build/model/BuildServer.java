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
     * The name of the build server.
     * May be seen as a type of a build server (eg. TeamCity, Travis CI, GitLab).
     */
    private BuildServerName name;

    /**
     * The projects configured on the build server.
     */
    private List<Project> projects;

    /**
     * Creates a new instance of a build server.
     *
     * @param name     the name of the build server
     * @param projects the projects configured on the build server
     */
    public BuildServer(BuildServerName name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    /**
     * Gets the name of the build server.
     *
     * @return the name of the build server.
     */
    public BuildServerName getName() {
        return this.name;
    }

    /**
     * Gets the projects of the build server.
     *
     * @return the projects of the build server
     */
    public List<Project> getProjects() {
        return this.projects;
    }

    /**
     * Gets a specific project of the build server by its name.
     *
     * @param projectName the name of the project to retrieve
     * @return the specified project of the build server or {@code null} if no project matches the provided name
     */
    public Project getProject(String projectName) {
        for (Project project : this.projects) {
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

        for (Project project : this.projects) {
            projectNames.add(project.getName());
        }

        return projectNames;
    }
}
