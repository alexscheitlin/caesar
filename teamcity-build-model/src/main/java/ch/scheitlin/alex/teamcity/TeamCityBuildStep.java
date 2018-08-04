package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.List;

public class TeamCityBuildStep {
    private int number;
    private String name;
    private BuildStepRunnerType runnerType;
    private String duration;
    private int exitCode;
    private List<TeamCityBuildLogEntry> buildLogEntries;

    public TeamCityBuildStep(int number, String name, BuildStepRunnerType runnerType, String duration) {
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

    public BuildStepRunnerType getRunnerType() {
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

    public void addBuildLogEntry(TeamCityBuildLogEntry buildLogEntry) {
        this.buildLogEntries.add(buildLogEntry);
    }

    public String getOutput() {
        StringBuilder builder = new StringBuilder();
        for (TeamCityBuildLogEntry entry : this.buildLogEntries) {
            builder.append(entry.getStepOutput());
            builder.append("\n");
        }

        return builder.toString();
    }

    public String getCleanOutput() {
        StringBuilder builder = new StringBuilder();
        for (TeamCityBuildLogEntry entry : this.buildLogEntries) {
            if (!entry.getStepOutput().contains("[Maven Watcher]") &&
                    !entry.getStepOutput().contains("##teamcity[")) {
                builder.append(entry.getStepOutput());
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
