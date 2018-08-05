package ch.scheitlin.alex.build.model;

import java.util.List;

public class Branch {
    private String name;
    private List<Build> builds;

    public Branch(String name, List<Build> builds) {
        this.name = name;
        this.builds = builds;
    }

    public String getName() {
        return this.name;
    }

    public List<Build> getBuilds() {
        return this.builds;
    }
}
