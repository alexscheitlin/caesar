package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MavenGoalTest {

    @Test
    public void setAndGetVendor() {
        String expectedVendor = "Vendor";

        MavenGoal goal = new MavenGoal();
        goal.setVendor(expectedVendor);

        String actualVendor = goal.getVendor();

        Assert.assertEquals(expectedVendor, actualVendor);
    }

    @Test
    public void setAndGetPlugin() {
        String expectedPlugin = "Plugin";

        MavenGoal goal = new MavenGoal();
        goal.setPlugin(expectedPlugin);

        String actualPlugin = goal.getPlugin();

        Assert.assertEquals(expectedPlugin, actualPlugin);
    }

    @Test
    public void setAndGetVersion() {
        String expectedVersion = "Version";

        MavenGoal goal = new MavenGoal();
        goal.setVersion(expectedVersion);

        String actualVersion = goal.getVersion();

        Assert.assertEquals(expectedVersion, actualVersion);
    }

    @Test
    public void setAndGetName() {
        String expectedName = "Name";

        MavenGoal goal = new MavenGoal();
        goal.setName(expectedName);

        String actualName = goal.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setAndGetInformation() {
        String expectedInformation = "Information";

        MavenGoal goal = new MavenGoal();
        goal.setInformation(expectedInformation);

        String actualInformation = goal.getInformation();

        Assert.assertEquals(expectedInformation, actualInformation);
    }

    @Test
    public void setAndGetModule() {
        String expectedModule = "Module";

        MavenGoal goal = new MavenGoal();
        goal.setModule(expectedModule);

        String actualModule = goal.getModule();

        Assert.assertEquals(expectedModule, actualModule);
    }

    @Test
    public void setAndGetMessage() {
        String expectedMessage = "Message";

        MavenGoal goal = new MavenGoal();
        goal.setMessage(expectedMessage);

        String actualMessage = goal.getMessage();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void setAndGetLines() {
        List<String> expectedLines = new ArrayList<String>();

        MavenGoal goal = new MavenGoal();
        goal.setLines(expectedLines);

        List<String> actualLines = goal.getLines();

        Assert.assertEquals(expectedLines, actualLines);
    }

    @Test
    public void toStringTest() {
        String expectedString = "plugin:version:name (information) @ module";

        MavenGoal goal = new MavenGoal();
        goal.setPlugin("plugin");
        goal.setVersion("version");
        goal.setName("name");
        goal.setInformation("information");
        goal.setModule("module");

        String actualString = goal.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void addLineAndGetLinesAsMultiLineString() {
        String line1 = "1: First Line";
        String line2 = "2: Second Line";
        String expectedString = line1 + "\n" + line2 + "\n";

        MavenGoal goal = new MavenGoal();
        goal.addLine(line1);
        goal.addLine(line2);

        String actualString = goal.getLinesAsMultiLineString();

        Assert.assertEquals(expectedString, actualString);
    }
}