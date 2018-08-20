package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerBuildConfigurationTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "configurationName";

        // allocate test objects
        BuildServerBuildConfiguration config = new BuildServerBuildConfiguration(expectedName, null);

        // execute method to be tested
        String actualName = config.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBranches() {
        // assign variables with test data
        List<BuildServerBranch> expectedBranches = new ArrayList<BuildServerBranch>();

        // allocate test objects
        BuildServerBuildConfiguration config = new BuildServerBuildConfiguration(null, expectedBranches);

        // execute method to be tested
        List<BuildServerBranch> actualBranches = config.getBranches();

        // assert result
        Assert.assertEquals(expectedBranches, actualBranches);
    }
}