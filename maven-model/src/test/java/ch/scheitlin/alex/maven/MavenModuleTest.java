package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MavenModuleTest {

    @Test
    public void getName() {
        String expectedName = "Name";

        MavenModule module = new MavenModule(expectedName);

        String actualName = module.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setAndGetVersion() {
        String expectedVersion = "version";

        MavenModule module = new MavenModule(null);
        module.setVersion(expectedVersion);

        String actualVersion = module.getVersion();

        Assert.assertEquals(expectedVersion, actualVersion);
    }

    @Test
    public void setAndGetStatus() {
        MavenModuleStatus expectedStatus = MavenModuleStatus.SUCCESS;

        MavenModule module = new MavenModule(null);
        module.setStatus(expectedStatus);

        MavenModuleStatus actualStatus = module.getStatus();

        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void setAndGetDuration() {
        String expectedDuration = "Duration";

        MavenModule module = new MavenModule(null);
        module.setDuration(expectedDuration);

        String actualDuration = module.getDuration();

        Assert.assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void setAndGetGoals() {
        List<MavenGoal> expectedGoals = new ArrayList<MavenGoal>();

        MavenModule module = new MavenModule(null);
        module.setGoals(expectedGoals);

        List<MavenGoal> actualGoals = module.getGoals();

        Assert.assertEquals(expectedGoals, actualGoals);
    }

    @Test
    public void addGoal() {
        MavenGoal expectedGoal = new MavenGoal();

        MavenModule module = new MavenModule(null);
        module.addGoal(expectedGoal);

        MavenGoal actualGoal = module.getGoals().get(0);

        Assert.assertEquals(expectedGoal, actualGoal);
    }
}