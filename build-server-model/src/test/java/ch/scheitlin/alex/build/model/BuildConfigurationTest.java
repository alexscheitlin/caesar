package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildConfigurationTest {

    @Test
    public void getName() {
        String expectedName = "configurationName";
        List<Branch> branches = null;

        BuildConfiguration config = new BuildConfiguration(expectedName, branches);

        String actualName = config.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBranches() {
        Branch branch = new Branch(null, null);

        List<Branch> expectedBranches = new ArrayList<Branch>();
        expectedBranches.add(branch);

        BuildConfiguration config = new BuildConfiguration(null, expectedBranches);

        List<Branch> actualBranches = config.getBranches();

        Assert.assertEquals(expectedBranches, actualBranches);
    }
}