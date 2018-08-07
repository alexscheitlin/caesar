package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.List;

public class TeamCityBuildStep {
    private int number;
    private String name;
    private TeamCityBuildStepRunnerType runnerType;
    private String duration;
    private int exitCode;
    private List<TeamCityBuildLogEntry> buildLogEntries;

    public TeamCityBuildStep(int number, String name, TeamCityBuildStepRunnerType runnerType, String duration) {
        this.number = number;
        this.name = name;
        this.runnerType = runnerType;
        this.duration = duration;
        this.exitCode = -1; // unknown
        this.buildLogEntries = new ArrayList<TeamCityBuildLogEntry>();
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public TeamCityBuildStepRunnerType getRunnerType() {
        return this.runnerType;
    }

    public String getDuration() {
        return this.duration;
    }

    public int getExitCode() {
        return this.exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public List<TeamCityBuildLogEntry> getBuildLogEntries() {
        return buildLogEntries;
    }

    public void addBuildLogEntry(TeamCityBuildLogEntry entry) {
        this.buildLogEntries.add(entry);
    }

    public String getOutput() {
        StringBuilder builder = new StringBuilder();
        for (TeamCityBuildLogEntry entry : this.buildLogEntries) {
            builder.append(entry.getStepOutput());
            builder.append("\n");
        }

        return builder.toString();
    }

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
