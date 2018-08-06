package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TeamCityBuildStepTest {

    @Test
    public void getNumber() {
        int expectedNumber = 1;

        TeamCityBuildStep buildStep = new TeamCityBuildStep(expectedNumber, null, null, null);

        int actualNumber = buildStep.getNumber();

        Assert.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getName() {
        String expectedName = "Test";

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, expectedName, null, null);

        String actualName = buildStep.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getRunnerType() {
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.MAVEN;

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, expectedRunnerType, null);

        BuildStepRunnerType actualRunnerType = buildStep.getRunnerType();

        Assert.assertEquals(expectedRunnerType, actualRunnerType);
    }

    @Test
    public void getDuration() {
        String expectedDuration = "13s";

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, expectedDuration);

        String actualDuration = buildStep.getDuration();

        Assert.assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void getDefaultExitCode() {
        int expectedExitCode = -1;

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        int actualExitCode = buildStep.getExitCode();

        Assert.assertEquals(expectedExitCode, actualExitCode);
    }

    @Test
    public void setExitCode() {
        int expectedExitCode = 1;

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        buildStep.setExitCode(expectedExitCode);

        int actualExitCode = buildStep.getExitCode();

        Assert.assertEquals(expectedExitCode, actualExitCode);
    }

    @Test
    public void getDefaultBuildLogEntries() {
        int expectedNumberOfBuildLogEntries = 0;

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);

        int actualNumberOfBuildLogEntries = buildStep.getBuildLogEntries().size();

        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
    }

    @Test
    public void addBuildLogEntry() {
        int expectedNumberOfBuildLogEntries = 1;
        TeamCityBuildLogEntry expectedBuildLogEntry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        buildStep.addBuildLogEntry(expectedBuildLogEntry);

        List<TeamCityBuildLogEntry> buildLogEntries = buildStep.getBuildLogEntries();

        int actualNumberOfBuildLogEntries = buildLogEntries.size();
        TeamCityBuildLogEntry actualBuildLogEntry = buildLogEntries.get(0);

        Assert.assertEquals(expectedNumberOfBuildLogEntries, actualNumberOfBuildLogEntries);
        Assert.assertEquals(expectedBuildLogEntry, actualBuildLogEntry);
    }

    @Test
    public void getOutput() {
        String line1 = "Line 1";
        String line2 = "[Step 1/2]";
        String line3 = "[Step 1/2] Message";
        String expectedOutput = "Line 1" + "\n" + "" + "\n" + "Message" + "\n";

        TeamCityBuildLogEntry buildLogEntry1 = new TeamCityBuildLogEntry(1, null, null, 1, line1);
        TeamCityBuildLogEntry buildLogEntry2 = new TeamCityBuildLogEntry(2, null, null, 1, line2);
        TeamCityBuildLogEntry buildLogEntry3 = new TeamCityBuildLogEntry(3, null, null, 1, line3);

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        buildStep.addBuildLogEntry(buildLogEntry1);
        buildStep.addBuildLogEntry(buildLogEntry2);
        buildStep.addBuildLogEntry(buildLogEntry3);

        String actualOutput = buildStep.getOutput();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getCleanLog() {
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

        TeamCityBuildStep buildStep = new TeamCityBuildStep(0, null, null, null);
        for (String line : originalString.split("\n")) {
            buildStep.addBuildLogEntry(new TeamCityBuildLogEntry(1, null, null, 1, line));
        }

        String actualOutput = buildStep.getCleanLog();

        Assert.assertEquals(expectedOutput, actualOutput);
    }
}