package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "projectName";

        // allocate test objects
        Project project = new Project(expectedName, null);

        // execute method to be tested
        String actualName = project.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuildConfigurations() {
        // assign variables with test data
        List<BuildConfiguration> expectedBuildConfigurations = new ArrayList<BuildConfiguration>();

        // allocate test objects
        Project project = new Project(null, expectedBuildConfigurations);

        // execute method to be tested
        List<BuildConfiguration> actualBuildConfigurations = project.getBuildConfigurations();

        // assert result
        Assert.assertEquals(expectedBuildConfigurations, actualBuildConfigurations);
    }

    @Test
    public void getBuildConfigurationNames() {
        // assign variables with test data
        String[] expectedBuildConfigurationNames = { "BuildConfiguration", "BuildConfiguration2" };

        // allocate test objects
        BuildConfiguration buildConfiguration1 = new BuildConfiguration(expectedBuildConfigurationNames[0], null);
        BuildConfiguration buildConfiguration2 = new BuildConfiguration(expectedBuildConfigurationNames[1], null);

        List<BuildConfiguration> buildConfigurations = new ArrayList<BuildConfiguration>();
        buildConfigurations.add(buildConfiguration1);
        buildConfigurations.add(buildConfiguration2);

        Project project = new Project(null, buildConfigurations);

        // execute method to be tested
        List<String> actualBuildConfigurationNames = project.getBuildConfigurationNames();

        // assert result
        Assert.assertEquals(expectedBuildConfigurationNames.length, actualBuildConfigurationNames.size());

        for (int i = 0; i < expectedBuildConfigurationNames.length; i++) {
            Assert.assertEquals(expectedBuildConfigurationNames[i], actualBuildConfigurationNames.get(i));
        }
    }
}