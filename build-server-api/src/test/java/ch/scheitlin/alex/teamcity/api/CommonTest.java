package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;
import org.junit.Test;

import java.util.List;

// these tests only execute the methods and are designed for visual console testing
public class CommonTest extends TestUtils {
    TeamCityInstance teamCity = TeamCityInstanceFactory.httpAuth(this.host, this.username, this.password);

    @Test
    public void getProjects() {
        List<Project> projects = Common.getProjects(this.teamCity);

        System.out.println("Projects:");
        for (Project project : projects) {
            System.out.println("\t" + project.getName());
        }
        System.out.println();
    }

    @Test
    public void getBuildConfigurationsOfProject() {
        List<Project> projects = Common.getProjects(this.teamCity);
        Project project = projects.get(0);
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());

        System.out.println(project.getName());
        System.out.println("\tBuild Configurations:");
        for (BuildConfiguration buildConfiguration : buildConfigurations) {
            System.out.println("\t\t" + buildConfiguration.getName());
        }
        System.out.println();
    }

    @Test
    public void getBuildsOfBuildConfiguration() {
        List<Project> projects = Common.getProjects(this.teamCity);
        Project project = projects.get(0);
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());
        BuildConfiguration buildConfiguration = buildConfigurations.get(0);
        List<Build> builds = Common.getBuildsOfBuildConfiguration(this.teamCity, buildConfiguration.getId());

        System.out.println(project.getName());
        System.out.println("\tBuild Configurations:");
        System.out.println("\t\t" + buildConfiguration.getName());
        System.out.println("\t\t\tBuilds:");
        for (Build build : builds) {
            System.out.println("\t\t\t\t" + build.getStatus());
        }
        System.out.println();
    }

    @Test
    public void getVcsRootOfRevision() {
        // TODO: add visual console test
    }
}