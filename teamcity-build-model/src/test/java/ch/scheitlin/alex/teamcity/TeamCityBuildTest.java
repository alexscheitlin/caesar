package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamCityBuildTest {

    @Test
    public void setAndGetProjectName() {
        String expectedProjectName = "projectName";

        TeamCityBuild build = new TeamCityBuild();
        build.setProjectName(expectedProjectName);

        String actualProjectName = build.getProjectName();

        Assert.assertEquals(expectedProjectName, actualProjectName);
    }

    @Test
    public void setAndGetBuildConfigurationName() {
        String expectedBuildConfigurationName = "buildConfigurationName";

        TeamCityBuild build = new TeamCityBuild();
        build.setBuildConfigurationName(expectedBuildConfigurationName);

        String actualBuildConfigurationName = build.getBuildConfigurationName();

        Assert.assertEquals(expectedBuildConfigurationName, actualBuildConfigurationName);
    }

    @Test
    public void setAndGetNumber() {
        String expectedNumber = "number";

        TeamCityBuild build = new TeamCityBuild();
        build.setNumber(expectedNumber);

        String actualNumber = build.getNumber();

        Assert.assertEquals(expectedNumber, actualNumber);
    }


    @Test
    public void setAndGetBranchName() {
        String expectedBranchName = "branchName";

        TeamCityBuild build = new TeamCityBuild();
        build.setBranchName(expectedBranchName);

        String actualBranchName = build.getBranchName();

        Assert.assertEquals(expectedBranchName, actualBranchName);
    }

    @Test
    public void setAndGetStartDate() {
        long milliseconds = 1533567988151L;
        Date expectedStartDate = new Date(milliseconds);

        TeamCityBuild build = new TeamCityBuild();
        build.setStartDate(expectedStartDate);

        Date actualStartDate = build.getStartDate();

        Assert.assertEquals(expectedStartDate, actualStartDate);
    }

    @Test
    public void setAndGetFinishDate() {
        long milliseconds = 1533567988151L;
        Date expectedFinishDate = new Date(milliseconds);

        TeamCityBuild build = new TeamCityBuild();
        build.setStartDate(expectedFinishDate);

        Date actualFinishDate = build.getStartDate();

        Assert.assertEquals(expectedFinishDate, actualFinishDate);
    }

    @Test
    public void setAndGetStatus() {
        TeamCityBuildStatus expectedStatus = TeamCityBuildStatus.SUCCESS;

        TeamCityBuild build = new TeamCityBuild();
        build.setStatus(expectedStatus);

        TeamCityBuildStatus actualStatus = build.getStatus();

        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void setAndGetStatusText() {
        String expectedStatusText = "statusText";

        TeamCityBuild build = new TeamCityBuild();
        build.setStatusText(expectedStatusText);

        String actualStatusText = build.getStatusText();

        Assert.assertEquals(expectedStatusText, actualStatusText);
    }

    @Test
    public void setAndGetVcsRootName() {
        String expectedVcsRootName = "vcsRootName";

        TeamCityBuild build = new TeamCityBuild();
        build.setVcsRootName(expectedVcsRootName);

        String actualVcsRootName = build.getVcsRootName();

        Assert.assertEquals(expectedVcsRootName, actualVcsRootName);
    }

    @Test
    public void setAndGetCommitHash() {
        String expectedCommitHash = "commitHash";

        TeamCityBuild build = new TeamCityBuild();
        build.setCommitHash(expectedCommitHash);

        String actualCommitHash = build.getCommitHash();

        Assert.assertEquals(expectedCommitHash, actualCommitHash);
    }

    @Test
    public void setAndGetTeamCityServerVersion() {
        String expectedTeamCityServerVersion = "teamCityServerVersion";

        TeamCityBuild build = new TeamCityBuild();
        build.setTeamCityServerVersion(expectedTeamCityServerVersion);

        String actualTeamCityServerVersion = build.getTeamCityServerVersion();

        Assert.assertEquals(expectedTeamCityServerVersion, actualTeamCityServerVersion);
    }

    @Test
    public void getDefaultBuildLogEntries() {
        int expectedNumberOfBuildLogEntries = 0;

        TeamCityBuild build = new TeamCityBuild();

        int actualNumberOfBuildLogEntries = build.getBuildLogEntries().size();

        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
    }

    @Test
    public void setBuildLogEntries() {
        List<TeamCityBuildLogEntry> expectedBuildLogEntries = new ArrayList<TeamCityBuildLogEntry>();

        TeamCityBuild build = new TeamCityBuild();
        build.setBuildLogEntries(expectedBuildLogEntries);

        List<TeamCityBuildLogEntry> actualBuildLogEntries = build.getBuildLogEntries();

        Assert.assertEquals(expectedBuildLogEntries, actualBuildLogEntries);
    }

    @Test
    public void setAndGetBuildSteps() {
        List<TeamCityBuildStep> expectedBuildSteps = new ArrayList<TeamCityBuildStep>();

        TeamCityBuild build = new TeamCityBuild();
        build.setBuildSteps(expectedBuildSteps);

        List<TeamCityBuildStep> actualBuildSteps = build.getBuildSteps();

        Assert.assertEquals(expectedBuildSteps, actualBuildSteps);
    }

    @Test
    public void addBuildLogEntry() {
        int expectedNumberOfBuildLogEntries = 1;
        TeamCityBuildLogEntry expectedBuildLogEntry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        TeamCityBuild build = new TeamCityBuild();
        build.addBuildLogEntry(expectedBuildLogEntry);

        List<TeamCityBuildLogEntry> buildLogEntries = build.getBuildLogEntries();

        int actualNumberOfBuildLogEntries = buildLogEntries.size();
        TeamCityBuildLogEntry actualBuildLogEntry = buildLogEntries.get(0);

        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
        Assert.assertEquals(expectedBuildLogEntry, actualBuildLogEntry);
    }

    @Test
    public void getMavenLog() {
        String originalString = "[Step 1/1] [Maven Watcher] project started: bank_maven:bank_maven:jar:1.0-SNAPSHOT\n" +
                "[Step 1/1] bank_maven:bank_maven (1s)\n" +
                "[bank_maven:bank_maven] ##teamcity[importData tc:tags='tc:internal' ... logAsInternal='true']\n" +
                "[Step 1/1] Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n" +
                "[bank_maven:bank_maven] ##teamcity[importData ... logAsInternal='true']\n" +
                "[Step 1/1] [Maven Watcher]\n" +
                "[Step 1/1] ##teamcity[projectStarted tc:tags='tc:internal' ... testReportsDir1='C:/TeamCity/buildAgent/...']\n" +
                "[Step 1/1] Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n" +
                "[Step 1/1] Surefire report watcher\n" +
                "[Surefire report watcher] Watching paths:\n" +
                "[Surefire report watcher] C:\\TeamCity\\buildAgent\\...\\TEST-*.xml\n" +
                "[Step 1/1] Surefire report watcher\n" +
                "[Surefire report watcher] Watching paths:\n" +
                "[Surefire report watcher] C:\\TeamCity\\buildAgent\\...\\TEST-*.xml";

        String expectedMavenLog = "bank_maven:bank_maven (1s)\n" +
                "Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n" +
                "Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n";

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, TeamCityBuildStepRunnerType.MAVEN, null);
        for (String line : originalString.split("\n")) {
            buildStep.addBuildLogEntry(new TeamCityBuildLogEntry(1, null, null, 1, line));
        }

        List<TeamCityBuildStep> buildSteps = new ArrayList<TeamCityBuildStep>();
        buildSteps.add(buildStep);

        TeamCityBuild build = new TeamCityBuild();
        build.setBuildSteps(buildSteps);

        String actualMavenLog = build.getMavenLog();

        Assert.assertEquals(expectedMavenLog, actualMavenLog);
    }
}