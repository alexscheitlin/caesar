package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BranchTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "branchName";

        // allocate test objects
        Branch branch = new Branch(expectedName, null);

        // execute method to be tested
        String actualName = branch.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuilds() {
        // assign variables with test data
        List<Build> expectedBuilds = new ArrayList<Build>();

        // allocate test objects
        Branch branch = new Branch(null, expectedBuilds);

        // execute method to be tested
        List<Build> actualBuilds = branch.getBuilds();

        // assert result
        Assert.assertEquals(expectedBuilds, actualBuilds);
    }
}