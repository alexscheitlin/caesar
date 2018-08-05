package ch.scheitlin.alex.maven;

import java.util.ArrayList;
import java.util.List;

public class MavenGoal {
    private String vendor;
    private String plugin;
    private String version;
    private String name;
    private String information;
    private String module;
    private String message;
    private List<String> lines;

    public MavenGoal() {
        this.lines = new ArrayList<String>();
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPlugin() {
        return this.plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public String toString() {
        return this.plugin + ":" + this.version + ":" + this.name + " (" + this.information + ") @ " + this.module;
    }

    public String getLinesAsMultiLineString() {
        StringBuilder builder = new StringBuilder();
        for (String line : this.lines) {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }
}
