package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamCityBuild {
    private String projectName;
    private String buildConfigurationName;
    private String number;
    private String branchName;
    private Date startDate;
    private Date finishDate;
    private TeamCityBuildStatus status;
    private String statusText;
    private String vcsRootName;
    private String commitHash;
    private String teamCityServerVersion;
    private List<TeamCityBuildLogEntry> buildLogEntries;
    private List<TeamCityBuildStep> buildSteps;

    public TeamCityBuild() {
        this.buildLogEntries = new ArrayList<TeamCityBuildLogEntry>();
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }

    public String getBuildConfigurationName() {
        return this.buildConfigurationName;
    }

    public void setBuildConfigurationName(String name) {
        this.buildConfigurationName = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String name) {
        this.branchName = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(Date date) {
        this.finishDate = date;
    }

    public TeamCityBuildStatus getStatus() {
        return this.status;
    }

    public void setStatus(TeamCityBuildStatus status) {
        this.status = status;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public void setStatusText(String text) {
        this.statusText = text;
    }

    public String getVcsRootName() {
        return this.vcsRootName;
    }

    public void setVcsRootName(String name) {
        this.vcsRootName = name;
    }

    public String getCommitHash() {
        return this.commitHash;
    }

    public void setCommitHash(String hash) {
        this.commitHash = hash;
    }

    public String getTeamCityServerVersion() {
        return this.teamCityServerVersion;
    }

    public void setTeamCityServerVersion(String version) {
        this.teamCityServerVersion = version;
    }

    public List<TeamCityBuildLogEntry> getBuildLogEntries() {
        return this.buildLogEntries;
    }

    public void setBuildLogEntries(List<TeamCityBuildLogEntry> entries) {
        this.buildLogEntries = entries;
    }

    public List<TeamCityBuildStep> getBuildSteps() {
        return this.buildSteps;
    }

    public void setBuildSteps(List<TeamCityBuildStep> steps) {
        this.buildSteps = steps;
    }

    public void addBuildLogEntry(TeamCityBuildLogEntry entry) {
        this.buildLogEntries.add(entry);
    }

    public String getMavenLog() {
        for (TeamCityBuildStep step : buildSteps) {
            if (step.getRunnerType() == TeamCityBuildStepRunnerType.MAVEN) {
                return step.getCleanLog();
            }
        }

        return null;
    }
}
