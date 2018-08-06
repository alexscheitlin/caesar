package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamCityBuild {
    private String projectName;
    private String buildConfigurationName;
    private String buildNumber;
    private String branchName;
    private Date startDate;
    private Date finishDate;
    private BuildStatus status;
    private String statusText;
    private String vcsRootName;
    private String commitHash;
    private String teamCityServerVersion;
    private List<TeamCityBuildLogEntry> logEntries;
    private List<TeamCityBuildStep> teamCityBuildSteps;

    public TeamCityBuild() {
        this.logEntries = new ArrayList<TeamCityBuildLogEntry>();
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildConfigurationName() {
        return this.buildConfigurationName;
    }

    public void setBuildConfigurationName(String buildConfigurationName) {
        this.buildConfigurationName = buildConfigurationName;
    }

    public String getBuildNumber() {
        return this.buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public BuildStatus getStatus() {
        return this.status;
    }

    public void setStatus(BuildStatus status) {
        this.status = status;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getVcsRootName() {
        return this.vcsRootName;
    }

    public void setVcsRootName(String vcsRootName) {
        this.vcsRootName = vcsRootName;
    }

    public String getCommitHash() {
        return this.commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getTeamCityServerVersion() {
        return this.teamCityServerVersion;
    }

    public void setTeamCityServerVersion(String teamCityServerVersion) {
        this.teamCityServerVersion = teamCityServerVersion;
    }

    public List<TeamCityBuildLogEntry> getLogEntries() {
        return this.logEntries;
    }

    public void setLogEntries(List<TeamCityBuildLogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public List<TeamCityBuildStep> getTeamCityBuildSteps() {
        return this.teamCityBuildSteps;
    }

    public void setTeamCityBuildSteps(List<TeamCityBuildStep> teamCityBuildSteps) {
        this.teamCityBuildSteps = teamCityBuildSteps;
    }

    public void addLogEntry(TeamCityBuildLogEntry logEntry) {
        this.logEntries.add(logEntry);
    }

    public String getMavenLog() {
        for (TeamCityBuildStep step : teamCityBuildSteps) {
            if (step.getRunnerType() == BuildStepRunnerType.MAVEN) {
                return step.getCleanLog();
            }
        }

        return null;
    }
}
