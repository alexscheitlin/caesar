package ch.scheitlin.alex.maven.model;

import java.util.ArrayList;
import java.util.List;

public class MavenGoal {
    public String vendor;
    public String plugin;
    public String version;
    public String name;
    public String information;
    public String module;
    public String message;
    public List<String> lines;

    public MavenGoal() {
        this.lines = new ArrayList<String>();
    }

    public String toString() {
        return this.plugin + ":" + this.version + ":" + this.name + "(" + this.information + ") @ " + this.module;
    }
}
