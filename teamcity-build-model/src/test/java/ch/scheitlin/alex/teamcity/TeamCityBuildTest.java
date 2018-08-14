package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamCityBuildTest {

    @Test
    public void setAndGetProjectName() {
        // assign variables with test data
        String expectedProjectName = "projectName";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setProjectName(expectedProjectName);
        String actualProjectName = build.getProjectName();

        // assert result
        Assert.assertEquals(expectedProjectName, actualProjectName);
    }

    @Test
    public void setAndGetBuildConfigurationName() {
        // assign variables with test data
        String expectedBuildConfigurationName = "buildConfigurationName";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setBuildConfigurationName(expectedBuildConfigurationName);
        String actualBuildConfigurationName = build.getBuildConfigurationName();

        // assert result
        Assert.assertEquals(expectedBuildConfigurationName, actualBuildConfigurationName);
    }

    @Test
    public void setAndGetNumber() {
        // assign variables with test data
        String expectedNumber = "number";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setNumber(expectedNumber);
        String actualNumber = build.getNumber();

        // assert result
        Assert.assertEquals(expectedNumber, actualNumber);
    }


    @Test
    public void setAndGetBranchName() {
        // assign variables with test data
        String expectedBranchName = "branchName";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setBranchName(expectedBranchName);
        String actualBranchName = build.getBranchName();

        // assert result
        Assert.assertEquals(expectedBranchName, actualBranchName);
    }

    @Test
    public void setAndGetStartDate() {
        // assign variables with test data
        long milliseconds = 1533567988151L;
        Date expectedStartDate = new Date(milliseconds);

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setStartDate(expectedStartDate);
        Date actualStartDate = build.getStartDate();

        // assert result
        Assert.assertEquals(expectedStartDate, actualStartDate);
    }

    @Test
    public void setAndGetFinishDate() {
        // assign variables with test data
        long milliseconds = 1533567988151L;
        Date expectedFinishDate = new Date(milliseconds);

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setStartDate(expectedFinishDate);
        Date actualFinishDate = build.getStartDate();

        // assert result
        Assert.assertEquals(expectedFinishDate, actualFinishDate);
    }

    @Test
    public void setAndGetStatus() {
        // assign variables with test data
        TeamCityBuildStatus expectedStatus = TeamCityBuildStatus.SUCCESS;

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setStatus(expectedStatus);
        TeamCityBuildStatus actualStatus = build.getStatus();

        // assert result
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void setAndGetStatusText() {
        // assign variables with test data
        String expectedStatusText = "statusText";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setStatusText(expectedStatusText);
        String actualStatusText = build.getStatusText();

        // assert result
        Assert.assertEquals(expectedStatusText, actualStatusText);
    }

    @Test
    public void setAndGetVcsRootName() {
        // assign variables with test data
        String expectedVcsRootName = "vcsRootName";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setVcsRootName(expectedVcsRootName);
        String actualVcsRootName = build.getVcsRootName();

        // assert result
        Assert.assertEquals(expectedVcsRootName, actualVcsRootName);
    }

    @Test
    public void setAndGetCommitHash() {
        // assign variables with test data
        String expectedCommitHash = "commitHash";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setCommitHash(expectedCommitHash);
        String actualCommitHash = build.getCommitHash();

        // assert result
        Assert.assertEquals(expectedCommitHash, actualCommitHash);
    }

    @Test
    public void setAndGetTeamCityServerVersion() {
        // assign variables with test data
        String expectedTeamCityServerVersion = "teamCityServerVersion";

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setTeamCityServerVersion(expectedTeamCityServerVersion);
        String actualTeamCityServerVersion = build.getTeamCityServerVersion();

        // assert result
        Assert.assertEquals(expectedTeamCityServerVersion, actualTeamCityServerVersion);
    }

    @Test
    public void getDefaultBuildLogEntries() {
        // assign variables with test data
        int expectedNumberOfBuildLogEntries = 0;

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        int actualNumberOfBuildLogEntries = build.getBuildLogEntries().size();

        // assert result
        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
    }

    @Test
    public void setBuildLogEntries() {
        // assign variables with test data
        List<TeamCityBuildLogEntry> expectedBuildLogEntries = new ArrayList<TeamCityBuildLogEntry>();

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setBuildLogEntries(expectedBuildLogEntries);
        List<TeamCityBuildLogEntry> actualBuildLogEntries = build.getBuildLogEntries();

        // assert result
        Assert.assertEquals(expectedBuildLogEntries, actualBuildLogEntries);
    }

    @Test
    public void setAndGetBuildSteps() {
        // assign variables with test data
        List<TeamCityBuildStep> expectedBuildSteps = new ArrayList<TeamCityBuildStep>();

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.setBuildSteps(expectedBuildSteps);
        List<TeamCityBuildStep> actualBuildSteps = build.getBuildSteps();

        // assert result
        Assert.assertEquals(expectedBuildSteps, actualBuildSteps);
    }

    @Test
    public void addBuildLogEntry() {
        // assign variables with test data
        int expectedNumberOfBuildLogEntries = 1;
        TeamCityBuildLogEntry expectedBuildLogEntry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        // allocate test objects
        TeamCityBuild build = new TeamCityBuild();

        // execute method to be tested
        build.addBuildLogEntry(expectedBuildLogEntry);

        List<TeamCityBuildLogEntry> buildLogEntries = build.getBuildLogEntries();

        int actualNumberOfBuildLogEntries = buildLogEntries.size();
        TeamCityBuildLogEntry actualBuildLogEntry = buildLogEntries.get(0);

        // assert result
        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
        Assert.assertEquals(expectedBuildLogEntry, actualBuildLogEntry);
    }

    @Test
    public void getMavenLog() {
        // assign variables with test data
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

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, TeamCityBuildStepRunnerType.MAVEN, null);
        for (String line : originalString.split("\n")) {
            buildStep.addBuildLogEntry(new TeamCityBuildLogEntry(1, null, null, 1, line));
        }

        List<TeamCityBuildStep> buildSteps = new ArrayList<TeamCityBuildStep>();
        buildSteps.add(buildStep);

        TeamCityBuild build = new TeamCityBuild();
        build.setBuildSteps(buildSteps);

        // execute method to be tested
        String actualMavenLog = build.getMavenLog();

        // assert result
        Assert.assertEquals(expectedMavenLog, actualMavenLog);
    }
}
