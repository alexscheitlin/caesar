package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildId;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;
import org.junit.Test;

import java.util.List;

// these tests only execute the methods and are designed for visual console testing
public class BuildsTest extends TestUtils {
    TeamCityInstance teamCity = TeamCityInstanceFactory.httpAuth(this.host, this.username, this.password);

    @Test
    public void getStatusText() {
        BuildId buildId = getBuildId();
        String statusText = Builds.getStatusText(this.teamCity, buildId);

        System.out.println(statusText);
    }

    @Test
    public void getVscRootUrl() {
        BuildId buildId = getBuildId();
        String vcsRootUrl = Builds.getVscRootUrl(this.teamCity, buildId);

        System.out.println(vcsRootUrl);
    }

    @Test
    public void getBranchName() {
        BuildId buildId = getBuildId();
        String branchName = Builds.getBranchName(this.teamCity, buildId);

        System.out.println(branchName);
    }

    @Test
    public void getCommitHash() {
        BuildId buildId = getBuildId();
        String commitHash = Builds.getCommitHash(this.teamCity, buildId);

        System.out.println(commitHash);
    }

    private BuildId getBuildId() {
        // get first project
        List<Project> projects = Common.getProjects(this.teamCity);
        Project project = projects.get(0);

        // git first build configuration
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());
        BuildConfiguration buildConfiguration = buildConfigurations.get(0);

        // get first build
        List<Build> builds = Common.getBuildsOfBuildConfiguration(this.teamCity, buildConfiguration.getId());
        Build build = builds.get(0);

        return build.getId();
    }
}