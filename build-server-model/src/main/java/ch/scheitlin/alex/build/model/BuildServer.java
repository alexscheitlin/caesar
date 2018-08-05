package ch.scheitlin.alex.build.model;

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
}
