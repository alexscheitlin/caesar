package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MavenModuleTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "Name";

        // allocate test objects
        MavenModule module = new MavenModule(expectedName);

        // execute method to be tested
        String actualName = module.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setAndGetVersion() {
        // assign variables with test data
        String expectedVersion = "version";

        // allocate test objects
        MavenModule module = new MavenModule(null);

        // execute method to be tested
        module.setVersion(expectedVersion);
        String actualVersion = module.getVersion();

        // assert result
        Assert.assertEquals(expectedVersion, actualVersion);
    }

    @Test
    public void setAndGetStatus() {
        // assign variables with test data
        MavenModuleStatus expectedStatus = MavenModuleStatus.SUCCESS;

        // allocate test objects
        MavenModule module = new MavenModule(null);

        // execute method to be tested
        module.setStatus(expectedStatus);
        MavenModuleStatus actualStatus = module.getStatus();

        // assert result
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void setAndGetDuration() {
        // assign variables with test data
        String expectedDuration = "Duration";

        // allocate test objects
        MavenModule module = new MavenModule(null);

        // execute method to be tested
        module.setDuration(expectedDuration);
        String actualDuration = module.getDuration();

        // assert result
        Assert.assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void setAndGetGoals() {
        // assign variables with test data
        List<MavenGoal> expectedGoals = new ArrayList<MavenGoal>();

        // allocate test objects
        MavenModule module = new MavenModule(null);

        // execute method to be tested
        module.setGoals(expectedGoals);
        List<MavenGoal> actualGoals = module.getGoals();

        // assert result
        Assert.assertEquals(expectedGoals, actualGoals);
    }

    @Test
    public void addGoal() {
        // assign variables with test data
        MavenGoal expectedGoal = new MavenGoal();

        // allocate test objects
        MavenModule module = new MavenModule(null);
        module.addGoal(expectedGoal);

        // execute method to be tested
        MavenGoal actualGoal = module.getGoals().get(0);

        // assert result
        Assert.assertEquals(expectedGoal, actualGoal);
    }
}