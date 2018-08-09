package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildConfigurationTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "configurationName";

        // allocate test objects
        BuildConfiguration config = new BuildConfiguration(expectedName, null);

        // execute method to be tested
        String actualName = config.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBranches() {
        // assign variables with test data
        List<Branch> expectedBranches = new ArrayList<Branch>();

        // allocate test objects
        BuildConfiguration config = new BuildConfiguration(null, expectedBranches);

        // execute method to be tested
        List<Branch> actualBranches = config.getBranches();

        // assert result
        Assert.assertEquals(expectedBranches, actualBranches);
    }
}