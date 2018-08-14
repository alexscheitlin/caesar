package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MavenBuildTest {

    @Test
    public void getStatus() {
        // assign variables with test data
        MavenBuildStatus expectedStatus = MavenBuildStatus.SUCCESS;

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(expectedStatus, null, null,null);

        // execute method to be tested
        MavenBuildStatus actualStatus = mavenBuild.getStatus();

        // assert result
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getFailedGoal() {
        // assign variables with test data
        MavenGoal expectedFailedGoal = new MavenGoal();

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(null, expectedFailedGoal, null, null);

        // execute method to be tested
        MavenGoal actualFailedGoal = mavenBuild.getFailedGoal();

        // assert result
        Assert.assertEquals(expectedFailedGoal, actualFailedGoal);
    }

    @Test
    public void getErrorMessage() {
        // assign variables with test data
        String expectedErrorMessage = "Error Message";

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(null, null, expectedErrorMessage, null);

        // execute method to be tested
        String actualErrorMessage = mavenBuild.getErrorMessage();

        // assert result
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    @Test
    public void setAndGetModules() {
        // assign variables with test data
        List<MavenModule> expectedModules = new ArrayList<MavenModule>();

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(null, null, null, null);
        mavenBuild.setModules(expectedModules);

        // execute method to be tested
        List<MavenModule> actualModules = mavenBuild.getModules();

        // assert result
        Assert.assertEquals(expectedModules, actualModules);
    }

    @Test public void getBuildSummary() {
        // assign variables with test data
        String line1 = "Build";
        String line2 = "Summary";
        List<String> expectedBuildSummary = new ArrayList<String>();
        expectedBuildSummary.add(line1);
        expectedBuildSummary.add(line2);

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(null, null, null, expectedBuildSummary);

        // execute method to be tested
        List<String> actualBuildSummary = mavenBuild.getBuildSummary();

        // assert result
        Assert.assertEquals(expectedBuildSummary, actualBuildSummary);

    }
}
