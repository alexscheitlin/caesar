package ch.scheitlin.alex.teamcity.entities;

import ch.scheitlin.alex.teamcity.constants.BuildStepRunnerType;
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
        // TODO: add test
    }

    @Test
    public void getCleanOutput() {
        // TODO: add test
    }
}