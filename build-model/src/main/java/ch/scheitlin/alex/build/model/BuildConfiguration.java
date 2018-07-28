package ch.scheitlin.alex.build.model;

import java.util.List;

public class BuildConfiguration {
    private String name;
    private List<Branch> branches;

    public BuildConfiguration(String name, List<Branch> branches) {
        this.name = name;
        this.branches = branches;
    }

    public String getName() {
        return this.name;
    }

    public List<Branch> getBranches() {
        return this.branches;
    }
}
