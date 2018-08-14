package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team city build step that may be derived from a team city build log.
 */
public class TeamCityBuildStep {
    /**
     * The number of the build step within a build.
     */
    private int number;

    /**
     * The name of the build step.
     */
    private String name;

    /**
     * The runner type of the build step.
     */
    private TeamCityBuildStepRunnerType runnerType;

    /**
     * The duration of the execution of the build step.
     */
    private String duration;

    /**
     * The exit code of the build step (either {@code 0} (success) or {@code 1} (failure)).
     */
    private int exitCode;

    /**
     * The build log entries of the build step.
     */
    private List<TeamCityBuildLogEntry> buildLogEntries;

    /**
     * Creates a new instance of a build step.
     *
     * @param number     the number of the build step within a build
     * @param name       the name of the build step
     * @param runnerType the runner type of the build step
     * @param duration   the duration of the execution of the build step
     */
    public TeamCityBuildStep(int number, String name, TeamCityBuildStepRunnerType runnerType, String duration) {
        this.number = number;
        this.name = name;
        this.runnerType = runnerType;
        this.duration = duration;
        this.exitCode = -1; // unknown
        this.buildLogEntries = new ArrayList<TeamCityBuildLogEntry>();
    }

    /**
     * Gets the the number of the build step within a build.
     *
     * @return the number of the build step within a build
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Gets the name of the build step.
     *
     * @return the name of the build step
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the runner type of the build step.
     *
     * @return the runner type of the build step
     */
    public TeamCityBuildStepRunnerType getRunnerType() {
        return this.runnerType;
    }

    /**
     * Gets the duration of the execution of the build step.
     *
     * @return the duration of the execution of the build step
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Gets the exit code of the build step.
     *
     * @return {@code 0} if the build step was executed successfully or {@code 1} if not
     */
    public int getExitCode() {
        return this.exitCode;
    }

    /**
     * Sets the exit code of the build step.
     *
     * @param exitCode {@code 0} if the build step was executed successfully or {@code 1} if not
     */
    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    /**
     * Gets the build log entries of the build step.
     *
     * @return the build log entries of the build step
     */
    public List<TeamCityBuildLogEntry> getBuildLogEntries() {
        return buildLogEntries;
    }

    /**
     * Adds a build log entry of the build step.
     *
     * @param entry a build log entry of the build step
     */
    public void addBuildLogEntry(TeamCityBuildLogEntry entry) {
        this.buildLogEntries.add(entry);
    }

    /**
     * Gets the output of the build step (all log lines without the step indicator (e.g. 'Step [1/2]').
     *
     * @return the output of the build step as a multi line {@code String}
     */
    public String getOutput() {
        StringBuilder builder = new StringBuilder();
        for (TeamCityBuildLogEntry entry : this.buildLogEntries) {
            builder.append(entry.getStepOutput());
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * Gets the clean log of the build step (no leading step indicator (e.g. 'Step [1/2]') and removed as much log lines
     * introduced by team city as possible).
     *
     * @return the clean log of the build step as a multi line {@code String}
     */
    public String getCleanLog() {
        List<String> ignoreContaining = new ArrayList<String>();
        ignoreContaining.add("[Maven Watcher]");
        ignoreContaining.add("##teamcity[");
        ignoreContaining.add("Surefire report watcher");

        StringBuilder builder = new StringBuilder();
        for (TeamCityBuildLogEntry entry : this.buildLogEntries) {
            String stepOutput = entry.getStepOutput();
            if (!ignoreContaining.stream().anyMatch(s -> stepOutput.contains(s))) {
                builder.append(stepOutput);
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
