package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class BuildLogEntrySeverityTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all severities
    private void assertToString(BuildLogEntrySeverity severity, String expectedString) {
        String actualString = severity.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_UNKNOWN() {
        BuildLogEntrySeverity severity = BuildLogEntrySeverity.UNKNOWN;
        String expectedString = " ";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_INFORMATION() {
        BuildLogEntrySeverity severity = BuildLogEntrySeverity.INFORMATION;
        String expectedString = "i";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_WARNING() {
        BuildLogEntrySeverity severity = BuildLogEntrySeverity.WARNING;
        String expectedString = "W";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_ERROR() {
        BuildLogEntrySeverity severity = BuildLogEntrySeverity.ERROR;
        String expectedString = "E";

        assertToString(severity, expectedString);
    }

    @Test
    public void toString_FAILURE() {
        BuildLogEntrySeverity severity = BuildLogEntrySeverity.FAILURE;
        String expectedString = "F";

        assertToString(severity, expectedString);
    }
    // test toString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all severities
    private void assertFromString(String string, BuildLogEntrySeverity expectedSeverity) {
        BuildLogEntrySeverity actualSeverity = BuildLogEntrySeverity.fromString(string);

        Assert.assertEquals(expectedSeverity, actualSeverity);
    }

    @Test
    public void fromString_UNKNOWN() {
        String string = " ";
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.UNKNOWN;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_INFORMATION() {
        String string = "i";
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.INFORMATION;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_WARNING() {
        String string = "W";
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.WARNING;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_ERROR() {
        String string = "E";
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.ERROR;

        assertFromString(string, expectedSeverity);
    }

    @Test
    public void fromString_FAILURE() {
        String string = "F";
        BuildLogEntrySeverity expectedSeverity = BuildLogEntrySeverity.FAILURE;

        assertFromString(string, expectedSeverity);
    }
    // test fromString() method for all severities
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}