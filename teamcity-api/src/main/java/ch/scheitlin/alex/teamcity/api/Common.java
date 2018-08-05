package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.BuildConfigurationId;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.ProjectId;
import org.jetbrains.teamcity.rest.Revision;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.VcsRoot;
import org.jetbrains.teamcity.rest.VcsRootId;

import java.util.List;

/**
 * Provides common methods to interact with the team city rest api client.
 */
class Common {
    // get all projects configured in team city
    static List<Project> getProjects(TeamCityInstance teamCity) {
        return teamCity.rootProject().fetchChildProjects();
    }

    // get all build configurations of the project
    static List<BuildConfiguration> getBuildConfigurationsOfProject(TeamCityInstance teamCity, ProjectId projectId) {

        return teamCity.project(projectId).fetchBuildConfigurations();
    }

    // get all builds of the build configuration
    static List<Build> getBuildsOfBuildConfiguration(TeamCityInstance teamCity, BuildConfigurationId buildConfigurationId) {
        return teamCity
                .builds()
                .fromConfiguration(buildConfigurationId)
                .withAnyStatus()
                .withAllBranches()
                .list();
    }

    static VcsRoot getVcsRootOfRevision(TeamCityInstance teamCity, Revision revision) {
        VcsRootId vcsRootId = revision.getVcsRootInstance().getVcsRootId();
        return teamCity.vcsRoot(vcsRootId);
    }
}
