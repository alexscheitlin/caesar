package ch.scheitlin.alex.teamcity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a team city build that may be derived from a team city build log.
 */
public class TeamCityBuild {
    /**
     * The name of the project the build belongs to.
     */
    private String projectName;

    /**
     * The name of the build configuration the build belongs to.
     */
    private String buildConfigurationName;

    /**
     * The number of the build.
     */
    private String number;

    /**
     * The name of the branch of the version control system of the code of the build.
     */
    private String branchName;

    /**
     * The date and time when the build started.
     */
    private Date startDate;

    /**
     * The date and time when the build finished.
     */
    private Date finishDate;

    /**
     * The status of the build.
     */
    private TeamCityBuildStatus status;

    /**
     * The status text of the build.
     */
    private String statusText;

    /**
     * The name of the version control system root configured on team city.
     */
    private String vcsRootName;

    /**
     * The commit hash of the version control system of the code of the build.
     */
    private String commitHash;

    /**
     * The version of the used team city server.
     */
    private String teamCityServerVersion;

    /**
     * The log lines containing information about the build.
     */
    private List<TeamCityBuildLogEntry> buildLogEntries;

    /**
     * The build steps configured in the build configuration on team city.
     */
    private List<TeamCityBuildStep> buildSteps;

    /**
     * Creates a new instance of a team city build.
     */
    public TeamCityBuild() {
        this.buildLogEntries = new ArrayList<TeamCityBuildLogEntry>();
    }

    /**
     * Gets the name of the project the build belongs to.
     *
     * @return the name of the project the build belongs to
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Sets the name of the project the build belongs to.
     *
     * @param name the name of the project the build belongs to
     */
    public void setProjectName(String name) {
        this.projectName = name;
    }

    /**
     * Gets the name of the build configuration the build belongs to.
     *
     * @return the name of the build configuration the build belongs to
     */
    public String getBuildConfigurationName() {
        return this.buildConfigurationName;
    }

    /**
     * Sets the name of the build configuration the build belongs to.
     *
     * @param name the name of the build configuration the build belongs to
     */
    public void setBuildConfigurationName(String name) {
        this.buildConfigurationName = name;
    }

    /**
     * Gets the number of the build.
     *
     * @return the number of the build
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets the number of the build.
     *
     * @param number the number of the build
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the name of the branch of the version control system of the code of the build.
     *
     * @return the name of the branch of the version control system of the code of the build
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * Sets the name of the branch of the version control system of the code of the build.
     *
     * @param name the name of the branch of the version control system of the code of the build
     */
    public void setBranchName(String name) {
        this.branchName = name;
    }

    /**
     * Gets the date and time when the build started.
     *
     * @return the date and time when the build started
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Sets the date and time when the build started.
     *
     * @param date the date and time when the build started
     */
    public void setStartDate(Date date) {
        this.startDate = date;
    }

    /**
     * Gets the date and time when the build finished.
     *
     * @return the date and time when the build finished
     */
    public Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * Sets the date and time when the build finished.
     *
     * @param date the date and time when the build finished
     */
    public void setFinishDate(Date date) {
        this.finishDate = date;
    }

    /**
     * Gets the status of the build.
     *
     * @return the status of the build
     */
    public TeamCityBuildStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the build.
     *
     * @param status the status of the build
     */
    public void setStatus(TeamCityBuildStatus status) {
        this.status = status;
    }

    /**
     * Gets the status text of the build.
     *
     * @return the status text of the build
     */
    public String getStatusText() {
        return this.statusText;
    }

    /**
     * Sets the status text of the build.
     *
     * @param text the status text of the build
     */
    public void setStatusText(String text) {
        this.statusText = text;
    }

    /**
     * Gets the name of the version control system root configured on team city.
     *
     * @return the name of the version control system root configured on team city
     */
    public String getVcsRootName() {
        return this.vcsRootName;
    }

    /**
     * Sets the name of the version control system root configured on team city.
     *
     * @param name the name of the version control system root configured on team city
     */
    public void setVcsRootName(String name) {
        this.vcsRootName = name;
    }

    /**
     * Gets the commit hash of the version control system of the code of the build.
     *
     * @return the commit hash of the version control system of the code of the build
     */
    public String getCommitHash() {
        return this.commitHash;
    }

    /**
     * Sets the commit hash of the version control system of the code of the build.
     *
     * @param hash the commit hash of the version control system of the code of the build
     */
    public void setCommitHash(String hash) {
        this.commitHash = hash;
    }

    /**
     * Gets the version of the used team city server.
     *
     * @return the version of the used team city server
     */
    public String getTeamCityServerVersion() {
        return this.teamCityServerVersion;
    }

    /**
     * Sets the version of the used team city server.
     *
     * @param version the version of the used team city server
     */
    public void setTeamCityServerVersion(String version) {
        this.teamCityServerVersion = version;
    }

    /**
     * Gets the log lines containing information about the build.
     *
     * @return the log lines containing information about the build
     */
    public List<TeamCityBuildLogEntry> getBuildLogEntries() {
        return this.buildLogEntries;
    }

    /**
     * Sets the log lines containing information about the build.
     *
     * @param entries the log lines containing information about the build
     */
    public void setBuildLogEntries(List<TeamCityBuildLogEntry> entries) {
        this.buildLogEntries = entries;
    }

    /**
     * Gets the build steps configured in the build configuration on team city.
     *
     * @return the build steps configured in the build configuration on team city
     */
    public List<TeamCityBuildStep> getBuildSteps() {
        return this.buildSteps;
    }

    /**
     * Sets the build steps configured in the build configuration on team city.
     *
     * @param steps the build steps configured in the build configuration on team city
     */
    public void setBuildSteps(List<TeamCityBuildStep> steps) {
        this.buildSteps = steps;
    }

    /**
     * Adds a build step.
     *
     * @param entry the build step configured in the build configuration on team city
     */
    public void addBuildLogEntry(TeamCityBuildLogEntry entry) {
        this.buildLogEntries.add(entry);
    }

    /**
     * Gets the clean maven log (removed as much information added by team city as possible).
     *
     * @return the clean maven log
     */
    public String getMavenLog() {
        for (TeamCityBuildStep step : buildSteps) {
            if (step.getRunnerType() == TeamCityBuildStepRunnerType.MAVEN) {
                return step.getCleanLog();
            }
        }

        return null;
    }
}
