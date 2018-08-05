package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildTest {
    private String expectedId = "1";
    private String expectedNumber = "2";
    private boolean expectedStatus = true;
    private String expectedStatusText = "Successful";
    private String expectedRepository = "https://github.com/alexscheitlin/repository";
    private String expectedBranch = "master";
    private String expectedCommit = "1h89sg";

    @Test
    public void getId() {
        Build build = getDummyBuild();

        String actualId = build.getId();

        Assert.assertEquals(this.expectedId, actualId);
    }

    @Test
    public void getNumber() {
        Build build = getDummyBuild();

        String actualNumber = build.getNumber();

        Assert.assertEquals(this.expectedNumber, actualNumber);
    }

    @Test
    public void getStatus() {
        Build build = getDummyBuild();

        boolean actualStatus = build.getStatus();

        Assert.assertEquals(this.expectedStatus, actualStatus);
    }

    @Test
    public void getStatusText() {
        Build build = getDummyBuild();

        String actualStatusText = build.getStatusText();

        Assert.assertEquals(this.expectedStatusText, actualStatusText);
    }

    @Test
    public void getRepository() {
        Build build = getDummyBuild();

        String actualRepository = build.getRepository();

        Assert.assertEquals(this.expectedRepository, actualRepository);
    }

    @Test
    public void getBranch() {
        Build build = getDummyBuild();

        String actualBranch = build.getBranch();

        Assert.assertEquals(this.expectedBranch, actualBranch);
    }

    @Test
    public void getCommit() {
        Build build = getDummyBuild();

        String actualCommit = build.getCommit();

        Assert.assertEquals(this.expectedCommit, actualCommit);
    }

    private Build getDummyBuild() {
        return new Build(
                this.expectedId,
                this.expectedNumber,
                this.expectedStatus,
                this.expectedStatusText,
                this.expectedRepository,
                this.expectedBranch,
                this.expectedCommit);
    }
}