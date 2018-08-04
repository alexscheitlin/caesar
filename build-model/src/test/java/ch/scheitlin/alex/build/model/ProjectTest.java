package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectTest {

    @Test
    public void getName() {
        String expectedName = "projectName";
        List<BuildConfiguration> buildConfigurations = null;

        Project project = new Project(expectedName, buildConfigurations);

        String actualName = project.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuildConfigurations() {
        BuildConfiguration buildConfiguration = new BuildConfiguration(null, null);

        List<BuildConfiguration> expectedBuildConfigurations = new ArrayList<BuildConfiguration>();
        expectedBuildConfigurations.add(buildConfiguration);

        Project project = new Project(null, expectedBuildConfigurations);

        List<BuildConfiguration> actualBuildConfigurations = project.getBuildConfigurations();

        Assert.assertEquals(expectedBuildConfigurations, actualBuildConfigurations);
    }
}