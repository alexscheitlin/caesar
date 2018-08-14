package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildLogEntryTest {

    @Test
    public void getLineNumber() {
        // assign variables with test data
        int expectedLineNumber = 1;

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(expectedLineNumber, null, null, 0, null);

        // execute method to be tested
        int actualLineNumber = entry.getLineNumber();

        // assert result
        Assert.assertEquals(expectedLineNumber, actualLineNumber);
    }

    @Test
    public void getTime() {
        // assign variables with test data
        String expectedTime = "00:00:00";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, expectedTime, null, 0, null);

        // execute method to be tested
        String actualTime = entry.getTime();

        // assert result
        Assert.assertEquals(expectedTime, actualTime);
    }

    @Test
    public void getSeverity() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.INFORMATION;

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, expectedSeverity, 0, null);

        // execute method to be tested
        TeamCityBuildLogEntrySeverity actualSeverity = entry.getSeverity();

        // assert result
        Assert.assertEquals(expectedSeverity, actualSeverity);
    }

    @Test
    public void getLevel() {
        // assign variables with test data
        int expectedLevel = 1;

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, expectedLevel, null);

        // execute method to be tested
        int actualLevel = entry.getLevel();

        // assert result
        Assert.assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void getMessage() {
        // assign variables with test data
        String expectedMessage = "message";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, 0, expectedMessage);

        // execute method to be tested
        String actualMessage = entry.getMessage();

        // assert result
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void appendMessageLine() {
        // assign variables with test data
        String expectedMessage = "message";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(0, null, null, 0, null);

        // execute method to be tested
        entry.appendMessageLine(expectedMessage);
        String[] messageLines = entry.getMessage().split("\n");
        String actualMessage = messageLines[messageLines.length - 1];

        // assert result
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void toStringTest() {
        // assign variables with test data
        String expectedEntry = "  3: 00:00:00 \t\tMessage";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", TeamCityBuildLogEntrySeverity.INFORMATION, 2, "Message");

        // execute method to be tested
        String actualEntry = entry.toString();

        // assert result
        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void toOriginalString() {
        // assign variables with test data
        String expectedEntry = "[00:00:00]i: \t\tMessage";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", TeamCityBuildLogEntrySeverity.INFORMATION, 2, "Message");

        // execute method to be tested
        String actualEntry = entry.toOriginalString();

        // assert result
        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getStepEntryMessage() {
        // assign variables with test data
        String expectedEntry = "Message";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", TeamCityBuildLogEntrySeverity.INFORMATION, 2, "[Step 1/12] Message");

        // execute method to be tested
        String actualEntry = entry.getStepEntryMessage();

        // assert result
        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getOutput() {
        // assign variables with test data
        String expectedEntry = "\tMessage";

        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", TeamCityBuildLogEntrySeverity.INFORMATION, 2, "Message");

        // execute method to be tested
        String actualEntry = entry.getOutput();

        // assert result
        Assert.assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getStepOutput() {
        // assign variables with test data
        String expectedEntry = "\tMessage";

        // allocate test objects
        TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(3, "00:00:00", TeamCityBuildLogEntrySeverity.INFORMATION, 2, "[Step 1/12] Message");

        // execute method to be tested
        String actualEntry = entry.getStepOutput();

        // assert result
        Assert.assertEquals(expectedEntry, actualEntry);
    }
}
