package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildLogEntrySeverityTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all severities
    private void assertToString(TeamCityBuildLogEntrySeverity severity, String expectedString) {
        String actualString = severity.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_UNKNOWN() {
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.UNKNOWN;
        String expectedString = " ";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_INFORMATION() {
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.INFORMATION;
        String expectedString = "i";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_WARNING() {
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.WARNING;
        String expectedString = "W";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_ERROR() {
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.ERROR;
        String expectedString = "E";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_FAILURE() {
        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.FAILURE;
        String expectedString = "F";

        assertToString(severity, expectedString);
    }
    // test toString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all severities
    private void assertFromString(String string, TeamCityBuildLogEntrySeverity expectedSeverity) {
        TeamCityBuildLogEntrySeverity actualSeverity = TeamCityBuildLogEntrySeverity.fromString(string);

        Assert.assertEquals(expectedSeverity, actualSeverity);
    }

    @Test
    public void fromString_UNKNOWN() {
        String string = " ";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.UNKNOWN;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_INFORMATION() {
        String string = "i";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.INFORMATION;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_WARNING() {
        String string = "W";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.WARNING;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_ERROR() {
        String string = "E";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.ERROR;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_FAILURE() {
        String string = "F";
        TeamCityBuildLogEntrySeverity expectedSeverity = TeamCityBuildLogEntrySeverity.FAILURE;

        assertFromString(string, expectedSeverity);
    }
    // test fromString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}