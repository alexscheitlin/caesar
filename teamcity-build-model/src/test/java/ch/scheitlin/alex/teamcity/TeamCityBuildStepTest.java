package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TeamCityBuildStepTest {

    @Test
    public void getNumber() {
        // assign variables with test data
        int expectedNumber = 1;

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(expectedNumber, null, null, null);

        // execute method to be tested
        int actualNumber = buildStep.getNumber();

        // assert result
        Assert.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "Test";

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, expectedName, null, null);

        // execute method to be tested
        String actualName = buildStep.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getRunnerType() {
        // assign variables with test data
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MAVEN;

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, expectedRunnerType, null);

        // execute method to be tested
        TeamCityBuildStepRunnerType actualRunnerType = buildStep.getRunnerType();

        // assert result
        Assert.assertEquals(expectedRunnerType, actualRunnerType);
    }

    @Test
    public void getDuration() {
        // assign variables with test data
        String expectedDuration = "13s";

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, expectedDuration);

        // execute method to be tested
        String actualDuration = buildStep.getDuration();

        // assert result
        Assert.assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void getDefaultExitCode() {
        // assign variables with test data
        int expectedExitCode = -1;

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        // execute method to be tested
        int actualExitCode = buildStep.getExitCode();

        // assert result
        Assert.assertEquals(expectedExitCode, actualExitCode);
    }

    @Test
    public void setExitCode() {
        // assign variables with test data
        int expectedExitCode = 1;

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        // execute method to be tested
        buildStep.setExitCode(expectedExitCode);
        int actualExitCode = buildStep.getExitCode();

        // assert result
        Assert.assertEquals(expectedExitCode, actualExitCode);
    }

    @Test
    public void getDefaultBuildLogEntries() {
        // assign variables with test data
        int expectedNumberOfBuildLogEntries = 0;

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        // execute method to be tested
        int actualNumberOfBuildLogEntries = buildStep.getBuildLogEntries().size();

        // assert result
        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
    }

    @Test
    public void addBuildLogEntry() {
        // assign variables with test data
        int expectedNumberOfBuildLogEntries = 1;
        TeamCityBuildLogEntry expectedBuildLogEntry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        // execute method to be tested
        buildStep.addBuildLogEntry(expectedBuildLogEntry);

        List<TeamCityBuildLogEntry> buildLogEntries = buildStep.getBuildLogEntries();

        int actualNumberOfBuildLogEntries = buildLogEntries.size();
        TeamCityBuildLogEntry actualBuildLogEntry = buildLogEntries.get(0);

        // assert result
        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
        Assert.assertEquals(expectedBuildLogEntry, actualBuildLogEntry);
    }

    @Test
    public void getOutput() {
        // assign variables with test data
        String line1 = "Line 1";
        String line2 = "[Step 1/2]";
        String line3 = "[Step 1/2] Message";
        String expectedOutput = "Line 1" + "\n" + "" + "\n" + "Message" + "\n";

        // allocate test objects
        TeamCityBuildLogEntry buildLogEntry1 = new TeamCityBuildLogEntry(1, null, null, 1, line1);
        TeamCityBuildLogEntry buildLogEntry2 = new TeamCityBuildLogEntry(2, null, null, 1, line2);
        TeamCityBuildLogEntry buildLogEntry3 = new TeamCityBuildLogEntry(3, null, null, 1, line3);

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        buildStep.addBuildLogEntry(buildLogEntry1);
        buildStep.addBuildLogEntry(buildLogEntry2);
        buildStep.addBuildLogEntry(buildLogEntry3);

        // execute method to be tested
        String actualOutput = buildStep.getOutput();

        // assert result
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getCleanLog() {
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

        String expectedOutput = "bank_maven:bank_maven (1s)\n" +
                "Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n" +
                "Importing data from 'C:/TeamCity/buildAgent/.../TEST-*.xml' (not existing file) with 'surefire' processor\n";

        // allocate test objects
        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        for (String line : originalString.split("\n")) {
            buildStep.addBuildLogEntry(new TeamCityBuildLogEntry(1, null, null, 1, line));
        }

        // execute method to be tested
        String actualOutput = buildStep.getCleanLog();

        // assert result
        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
