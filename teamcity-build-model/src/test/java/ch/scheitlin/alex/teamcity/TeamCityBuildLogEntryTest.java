package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildLogEntryTest {

    @Test
    public void getLineNumber() {
        int expectedLineNumber = 1;

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(expectedLineNumber, null, null, 0, null);

        int actualLineNumber = entry.getLineNumber();

        Assert.assertEquals(expectedLineNumber, actualLineNumber);
    }

    @Test
    public void getTime() {
        String expectedTime = "00:00:00";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, expectedTime, null, 0, null);

        String actualTime = entry.getTime();

        Assert.assertEquals(expectedTime, actualTime);
    }

    @Test
    public void getSeverity() {
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.INFORMATION;

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, expectedSeverity, 0, null);

        BuildLogEntrySeverity actualSeverity = entry.getSeverity();

        Assert.assertEquals(expectedSeverity, actualSeverity);
    }

    @Test
    public void getLevel() {
        int expectedLevel = 1;

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, expectedLevel, null);

        int actualLevel = entry.getLevel();

        Assert.assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void getMessage() {
        String expectedMessage = "message";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, 0, expectedMessage);

        String actualMessage = entry.getMessage();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void appendMessage() {
        String expectedMessage = "message";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        entry.appendMessage(expectedMessage);

        String[] messageLines = entry.getMessage().split("\n");
        String actualMessage = messageLines[messageLines.length - 1];

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getEntry() {
        String expectedEntry = "  3: 00:00:00 \t\tMessage";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", BuildLogEntrySeverity.INFORMATION, 2, "Message");

        String actualEntry = entry.getEntry();

        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getOriginalEntry() {
        String expectedEntry = "[00:00:00]i: \t\tMessage";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", BuildLogEntrySeverity.INFORMATION, 2, "Message");

        String actualEntry = entry.getOriginalEntry();

        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getStepEntryMessage() {
        String expectedEntry = "Message";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", BuildLogEntrySeverity.INFORMATION, 2, "[Step 1/12] Message");

        String actualEntry = entry.getStepEntryMessage();

        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getOutput() {
        String expectedEntry = "\tMessage";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", BuildLogEntrySeverity.INFORMATION, 2, "Message");

        String actualEntry = entry.getOutput();

        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getStepOutput() {
        String expectedEntry = "\tMessage";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", BuildLogEntrySeverity.INFORMATION, 2, "[Step 1/12] Message");

        String actualEntry = entry.getStepOutput();

        Assert.assertEquals(expectedEntry, actualEntry);
    }
}