package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MavenGoalTest {

    @Test
    public void setAndGetPlugin() {
        // assign variables with test data
        MavenPlugin expectedPlugin = new MavenPlugin(null, null, null);

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setPlugin(expectedPlugin);
        MavenPlugin actualPlugin = goal.getPlugin();

        // assert result
        Assert.assertEquals(expectedPlugin, actualPlugin);
    }

    @Test
    public void setAndGetName() {
        // assign variables with test data
        String expectedName = "Name";

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setName(expectedName);
        String actualName = goal.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setAndGetInformation() {
        // assign variables with test data
        String expectedInformation = "Information";

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setInformation(expectedInformation);
        String actualInformation = goal.getInformation();

        // assert result
        Assert.assertEquals(expectedInformation, actualInformation);
    }

    @Test
    public void setAndGetModule() {
        // assign variables with test data
        String expectedModule = "Module";

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setModule(expectedModule);
        String actualModule = goal.getModule();

        // assert result
        Assert.assertEquals(expectedModule, actualModule);
    }

    @Test
    public void setAndGetMessage() {
        // assign variables with test data
        String expectedMessage = "Message";

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setMessage(expectedMessage);
        String actualMessage = goal.getMessage();

        // assert result
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void setAndGetLines() {
        // assign variables with test data
        List<String> expectedLines = new ArrayList<String>();

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.setLines(expectedLines);
        List<String> actualLines = goal.getLines();

        // assert result
        Assert.assertEquals(expectedLines, actualLines);
    }

    @Test
    public void toStringTest() {
        // assign variables with test data
        String expectedString = "plugin:version:name (information) @ module";

        // allocate test objects
        MavenGoal goal = new MavenGoal();
        goal.setPlugin(new MavenPlugin("plugin", "vendor", "version"));
        goal.setName("name");
        goal.setInformation("information");
        goal.setModule("module");

        // execute method to be tested
        String actualString = goal.toString();

        // assert result
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void addLineAndGetLinesAsMultiLineString() {
        // assign variables with test data
        String line1 = "1: First Line";
        String line2 = "2: Second Line";
        String expectedString = line1 + "\n" + line2 + "\n";

        // allocate test objects
        MavenGoal goal = new MavenGoal();

        // execute method to be tested
        goal.addLine(line1);
        goal.addLine(line2);
        String actualString = goal.getLinesAsMultiLineString();

        // assert result
        Assert.assertEquals(expectedString, actualString);
    }
}