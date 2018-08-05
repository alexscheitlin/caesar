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

    @Test
    public void getBuildConfigurationNames() {
        String[] expectedBuildConfigurationNames = { "BuildConfiguration", "BuildConfiguration2" };
        BuildConfiguration buildConfiguration1 = new BuildConfiguration(expectedBuildConfigurationNames[0], null);
        BuildConfiguration buildConfiguration2 = new BuildConfiguration(expectedBuildConfigurationNames[1], null);

        List<BuildConfiguration> buildConfigurations = new ArrayList<BuildConfiguration>();
        buildConfigurations.add(buildConfiguration1);
        buildConfigurations.add(buildConfiguration2);

        Project project = new Project(null, buildConfigurations);

        List<String> actualBuildConfiguratioNames = project.getBuildConfigurationNames();

        Assert.assertEquals(expectedBuildConfigurationNames.length, actualBuildConfiguratioNames.size());

        for (int i = 0; i < expectedBuildConfigurationNames.length; i++) {
            Assert.assertEquals(expectedBuildConfigurationNames[i], actualBuildConfiguratioNames.get(i));
        }
    }
}