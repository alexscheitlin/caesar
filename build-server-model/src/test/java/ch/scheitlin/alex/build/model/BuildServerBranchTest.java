package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerBranchTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "branchName";

        // allocate test objects
        BuildServerBranch branch = new BuildServerBranch(expectedName, null);

        // execute method to be tested
        String actualName = branch.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuilds() {
        // assign variables with test data
        List<BuildServerBuild> expectedBuilds = new ArrayList<BuildServerBuild>();

        // allocate test objects
        BuildServerBranch branch = new BuildServerBranch(null, expectedBuilds);

        // execute method to be tested
        List<BuildServerBuild> actualBuilds = branch.getBuilds();

        // assert result
        Assert.assertEquals(expectedBuilds, actualBuilds);
    }
}