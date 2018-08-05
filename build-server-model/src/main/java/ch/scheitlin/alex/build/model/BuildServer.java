package ch.scheitlin.alex.build.model;

import java.util.ArrayList;
import java.util.List;

public class BuildServer {
    private BuildServerName name;
    private List<Project> projects;

    public BuildServer(BuildServerName name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public BuildServerName getName() {
        return this.name;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public Project getProject(String projectName) {
        for (Project project : this.projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }

        return null;
    }

    public List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<String>();

        for (Project project : this.projects) {
            projectNames.add(project.getName());
        }

        return projectNames;
    }
}
