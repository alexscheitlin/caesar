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
        MavenBuild mavenBuild = new MavenBuild(expectedStatus, null);

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
        MavenBuild mavenBuild = new MavenBuild(null, expectedFailedGoal);

        // execute method to be tested
        MavenGoal actualFailedGoal = mavenBuild.getFailedGoal();

        // assert result
        Assert.assertEquals(expectedFailedGoal, actualFailedGoal);
    }

    @Test
    public void setAndGetModules() {
        // assign variables with test data
        List<MavenModule> expectedModules = new ArrayList<MavenModule>();

        // allocate test objects
        MavenBuild mavenBuild = new MavenBuild(null, null);
        mavenBuild.setModules(expectedModules);

        // execute method to be tested
        List<MavenModule> actualModules = mavenBuild.getModules();

        // assert result
        Assert.assertEquals(expectedModules, actualModules);
    }
}
