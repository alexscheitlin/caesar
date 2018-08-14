package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildLogEntrySeverityTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all severities
    private void assertToString(TeamCityBuildLogEntrySeverity severity, String expectedString) {
        // execute method to be tested
        String actualString = severity.toString();

        // assert result
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_UNKNOWN() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.UNKNOWN;
        String expectedString = " ";

        // execute method to be tested and assert result
        assertToString(severity, expectedString);
    }

    @Test
    public void toString_INFORMATION() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.INFORMATION;
        String expectedString = "i";

        // execute method to be tested and assert result
        assertToString(severity, expectedString);
    }

    @Test
    public void toString_WARNING() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.WARNING;
        String expectedString = "W";

        // execute method to be tested and assert result
        assertToString(severity, expectedString);
    }

    @Test
    public void toString_ERROR() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.ERROR;
        String expectedString = "E";

        // execute method to be tested and assert result
        assertToString(severity, expectedString);
    }

    @Test
    public void toString_FAILURE() {
        // assign variables with test data
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.FAILURE;
        String expectedString = "F";

        // execute method to be tested and assert result
        assertToString(severity, expectedString);
    }
    // test toString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all severities
    private void assertFromString(String string, TeamCityBuildLogEntrySeverity expectedSeverity) {
        // execute method to be tested
        TeamCityBuildLogEntrySeverity actualSeverity = TeamCityBuildLogEntrySeverity.fromString(string);

        // assert result
        Assert.assertEquals(expectedSeverity, actualSeverity);
    }

    @Test
    public void fromString_UNKNOWN() {
        // assign variables with test data
        String string = " ";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.UNKNOWN;

        // execute method to be tested and assert result
        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_INFORMATION() {
        // assign variables with test data
        String string = "i";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.INFORMATION;

        // execute method to be tested and assert result
        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_WARNING() {
        // assign variables with test data
        String string = "W";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.WARNING;

        // execute method to be tested and assert result
        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_ERROR() {
        // assign variables with test data
        String string = "E";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.ERROR;

        // execute method to be tested and assert result
        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_FAILURE() {
        // assign variables with test data
        String string = "F";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.FAILURE;

        // execute method to be tested and assert result
        assertFromString(string, expectedSeverity);
    }
    // test fromString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
