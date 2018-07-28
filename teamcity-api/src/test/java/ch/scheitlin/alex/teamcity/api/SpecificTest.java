package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;
import org.junit.Test;

import java.util.List;

// these tests only execute the methods and are designed for visual console testing
public class SpecificTest extends TestUtils {
    TeamCityInstance teamCity = TeamCityInstanceFactory.httpAuth(this.host, this.username, this.password);

    @Test
    public void getBuildsByBranches() {
        // get first project
        List<Project> projects = Common.getProjects(this.teamCity);
        Project project = projects.get(0);

        // git first build configuration
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());
        BuildConfiguration buildConfiguration = buildConfigurations.get(0);

        // get builds
        List<Build> builds = Common.getBuildsOfBuildConfiguration(this.teamCity, buildConfiguration.getId());

        // get custom configuration representation
        ch.scheitlin.alex.build.model.BuildConfiguration config = Specific.getBuildsByBranches(this.teamCity, buildConfiguration.getName(), builds);

        System.out.println(config.getName());
        for (ch.scheitlin.alex.build.model.Branch branch : config.getBranches()) {
            System.out.println("\t" + branch.getName());
            for (ch.scheitlin.alex.build.model.Build build : branch.getBuilds()) {
                System.out.println("\t\t" + build.getStatusText());
            }
        }
    }

    @Test
    public void getDistinctBranchNames() {
        // get first project
        List<Project> projects = Common.getProjects(this.teamCity);
        Project project = projects.get(0);

        // git first build configuration
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());
        BuildConfiguration buildConfiguration = buildConfigurations.get(0);

        // get builds
        List<Build> builds = Common.getBuildsOfBuildConfiguration(this.teamCity, buildConfiguration.getId());

        List<String> branchNames = Specific.getDistinctBranchNames(builds);

        for (String branchName : branchNames) {
            System.out.println(branchName);
        }
    }
}