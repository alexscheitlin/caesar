package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MavenBuildTest {

    @Test
    public void getStatus() {
        MavenBuildStatus expectedStatus = MavenBuildStatus.SUCCESS;

        MavenBuild mavenBuild = new MavenBuild(expectedStatus, null);

        MavenBuildStatus actualStatus = mavenBuild.getStatus();

        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getFailedGoal() {
        MavenGoal expectedFailedGoal = new MavenGoal();

        MavenBuild mavenBuild = new MavenBuild(null, expectedFailedGoal);

        MavenGoal actualFailedGoal = mavenBuild.getFailedGoal();

        Assert.assertEquals(expectedFailedGoal, actualFailedGoal);
    }

    @Test
    public void setAndGetModules() {
        List<MavenModule> expectedModules = new ArrayList<MavenModule>();

        MavenBuild mavenBuild = new MavenBuild(null, null);
        mavenBuild.setModules(expectedModules);

        List<MavenModule> actualModules = mavenBuild.getModules();

        Assert.assertEquals(expectedModules, actualModules);
    }
}