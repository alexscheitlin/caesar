package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildTest {

    @Test
    public void getId() {
        // assign variables with test data
        String expectedId = "1";

        // allocate test objects
        Build build = new Build(expectedId, null, false, null, null, null, null);

        // execute method to be tested
        String actualId = build.getId();

        // assert result
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void getNumber() {
        // assign variables with test data
        String expectedNumber = "2";

        // allocate test objects
        Build build = new Build(null, expectedNumber, false, null, null, null, null);

        // execute method to be tested
        String actualNumber = build.getNumber();

        // assert result
        Assert.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getStatus() {
        // assign variables with test data
        boolean expectedStatus = true;

        // allocate test objects
        Build build = new Build(null, null, expectedStatus, null, null, null, null);

        // execute method to be tested
        boolean actualStatus = build.getStatus();

        // assert result
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getStatusText() {
        // assign variables with test data
        String expectedStatusText = "Successful";

        // allocate test objects
        Build build = new Build(null, null, false, expectedStatusText, null, null, null);

        // execute method to be tested
        String actualStatusText = build.getStatusText();

        // assert result
        Assert.assertEquals(expectedStatusText, actualStatusText);
    }

    @Test
    public void getRepository() {
        // assign variables with test data
        String expectedRepository = "https://github.com/alexscheitlin/repository";

        // allocate test objects
        Build build = new Build(null, null, false, null, expectedRepository, null, null);

        // execute method to be tested
        String actualRepository = build.getRepository();

        // assert result
        Assert.assertEquals(expectedRepository, actualRepository);
    }

    @Test
    public void getBranch() {
        // assign variables with test data
        String expectedBranch = "master";

        // allocate test objects
        Build build = new Build(null, null, false, null, null, expectedBranch, null);

        // execute method to be tested
        String actualBranch = build.getBranch();

        // assert result
        Assert.assertEquals(expectedBranch, actualBranch);
    }

    @Test
    public void getCommit() {
        // assign variables with test data
        String expectedCommit = "1h89sg";

        // allocate test objects
        Build build = new Build(null, null, false, null, null, null, expectedCommit);

        // execute method to be tested
        String actualCommit = build.getCommit();

        // assert result
        Assert.assertEquals(expectedCommit, actualCommit);
    }
}