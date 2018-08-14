package ch.scheitlin.alex.maven;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an executed goal of the build of a maven module.
 */
public class MavenGoal {
    /**
     * The name of the vendor of the plugin containing the maven goal.
     */
    private String vendor;

    /**
     * The name of the plugin containing the maven goal.
     */
    private String plugin;

    /**
     * The version of the plugin containing the maven goal.
     */
    private String version;

    /**
     * The name of the maven goal.
     */
    private String name;

    /**
     * The information about the maven goal (e.g. 'default-clean' or 'default-compile').
     */
    private String information;

    /**
     * The name of the module of the maven goal.
     */
    private String module;

    /**
     * The error message if the execution of the maven goal failed.
     */
    private String message;

    /**
     * The log lines of the execution of the maven goal.
     */
    private List<String> lines;

    /**
     * Creates a new instance of a maven goal.
     */
    public MavenGoal() {
        this.lines = new ArrayList<String>();
    }

    /**
     * Gets the name of the vendor of the plugin containing the maven goal.
     *
     * @return the name of the vendor of the plugin containing the maven goal
     */
    public String getVendor() {
        return this.vendor;
    }

    /**
     * Sets the name of the vendor of the plugin containing the maven goal.
     *
     * @param vendor the name of the vendor of the plugin containing the maven goal
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Gets the name of the plugin containing the maven goal.
     *
     * @return the name of the plugin containing the maven goal
     */
    public String getPlugin() {
        return this.plugin;
    }

    /**
     * Sets the name of the plugin containing the maven goal.
     *
     * @param plugin the name of the plugin containing the maven goal
     */
    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets the version of the plugin containing the maven goal.
     *
     * @return the version of the plugin containing the maven goal
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version of the plugin containing the maven goal.
     *
     * @param version the version of the plugin containing the maven goal
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the name of the maven goal.
     *
     * @return the name of the maven goal
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the maven goal.
     *
     * @param name the name of the maven goal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the information about the maven goal (e.g. 'default-clean' or 'default-compile').
     *
     * @return the information about the maven goal (e.g. 'default-clean' or 'default-compile')
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets the information about the maven goal (e.g. 'default-clean' or 'default-compile').
     *
     * @param information the information about the maven goal (e.g. 'default-clean' or 'default-compile')
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets the name of the module of the maven goal.
     *
     * @return the name of the module of the maven goal
     */
    public String getModule() {
        return this.module;
    }

    /**
     * Sets the name of the module of the maven goal.
     *
     * @param module the name of the module of the maven goal
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the error message
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the log lines of the execution of the maven goal.
     *
     * @return the log lines of the execution of the maven goal
     */
    public List<String> getLines() {
        return this.lines;
    }

    /**
     * Sets the log lines of the execution of the maven goal.
     *
     * @param lines the log lines of the execution of the maven goal
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Adds a log line of the execution of the maven goal.
     *
     * @param line a log line of the execution of the maven goal
     */
    public void addLine(String line) {
        this.lines.add(line);
    }

    /**
     * Converts the maven goal to a {@code String}.
     *
     * @return the string representation of the maven goal
     */
    public String toString() {
        return this.plugin + ":" + this.version + ":" + this.name + " (" + this.information + ") @ " + this.module;
    }

    /**
     * Gets the log lines of the execution of the maven goal as a multi line {@code String}.
     *
     * @return the log lines of the execution of the maven goal
     */
    public String getLinesAsMultiLineString() {
        StringBuilder builder = new StringBuilder();
        for (String line : this.lines) {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }
}
