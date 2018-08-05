package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BranchTest {

    @Test
    public void getName() {
        String expectedName = "branchName";
        List<Build> builds = null;

        Branch branch = new Branch(expectedName, builds);

        String actualName = branch.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuilds() {
        Build build = new Build(null, null, false, null, null, null, null);

        List<Build> expectedBuilds = new ArrayList<Build>();
        expectedBuilds.add(build);

        Branch branch = new Branch(null, expectedBuilds);

        List<Build> actualBuilds = branch.getBuilds();

        Assert.assertEquals(expectedBuilds, actualBuilds);
    }
}