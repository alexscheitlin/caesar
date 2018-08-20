package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerProjectTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "projectName";

        // allocate test objects
        BuildServerProject project = new BuildServerProject(expectedName, null);

        // execute method to be tested
        String actualName = project.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getBuildConfigurations() {
        // assign variables with test data
        List<BuildServerBuildConfiguration> expectedBuildConfigurations = new ArrayList<BuildServerBuildConfiguration>();

        // allocate test objects
        BuildServerProject project = new BuildServerProject(null, expectedBuildConfigurations);

        // execute method to be tested
        List<BuildServerBuildConfiguration> actualBuildConfigurations = project.getBuildConfigurations();

        // assert result
        Assert.assertEquals(expectedBuildConfigurations, actualBuildConfigurations);
    }

    @Test
    public void getBuildConfigurationNames() {
        // assign variables with test data
        String[] expectedBuildConfigurationNames = { "BuildServerBuildConfiguration", "BuildConfiguration2" };

        // allocate test objects
        BuildServerBuildConfiguration buildConfiguration1 = new BuildServerBuildConfiguration(expectedBuildConfigurationNames[0], null);
        BuildServerBuildConfiguration buildConfiguration2 = new BuildServerBuildConfiguration(expectedBuildConfigurationNames[1], null);

        List<BuildServerBuildConfiguration> buildConfigurations = new ArrayList<BuildServerBuildConfiguration>();
        buildConfigurations.add(buildConfiguration1);
        buildConfigurations.add(buildConfiguration2);

        BuildServerProject project = new BuildServerProject(null, buildConfigurations);

        // execute method to be tested
        List<String> actualBuildConfigurationNames = project.getBuildConfigurationNames();

        // assert result
        Assert.assertEquals(expectedBuildConfigurationNames.length, actualBuildConfigurationNames.size());

        for (int i = 0; i < expectedBuildConfigurationNames.length; i++) {
            Assert.assertEquals(expectedBuildConfigurationNames[i], actualBuildConfigurationNames.get(i));
        }
    }
}