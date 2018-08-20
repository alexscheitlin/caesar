package ch.scheitlin.alex.teamcity.api;

import ch.scheitlin.alex.build.model.BuildServer;
import ch.scheitlin.alex.build.model.BuildServerType;
import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.TeamCityInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to map team city objects to the build server model.
 */
class Specific {
    /**
     * Gets information about projects, build configurations, branches, and builds from team city as a build server model.
     *
     * @param teamCity the team city instance to access the team city rest api client
     * @return a build server model
     */
    static BuildServer getBuildServerModel(TeamCityInstance teamCity) {
        BuildServerType type = BuildServerType.TEAM_CITY;
        List<ch.scheitlin.alex.build.model.Project> projects = Specific.getProjects(teamCity);

        return new BuildServer(type, projects);
    }

    /**
     * Gets team city projects as projects of the build server model.
     *
     * @param teamCity the team city instance to access the team city rest api client
     * @return a list of projects of the project model
     */
    private static List<ch.scheitlin.alex.build.model.Project> getProjects(TeamCityInstance teamCity) {
        // fetch projects form team city
        List<Project> teamCityProjects = Common.getProjects(teamCity);

        // map all team city projects to the project model
        List<ch.scheitlin.alex.build.model.Project> projects = new ArrayList<ch.scheitlin.alex.build.model.Project>();
        for (Project teamCityProject : teamCityProjects) {
            // get all components for the project model
            String name = teamCityProject.getName();
            List<ch.scheitlin.alex.build.model.BuildConfiguration> buildConfigurations = getBuildConfigurations(teamCity, teamCityProject);

            // create the project model
            ch.scheitlin.alex.build.model.Project project = new ch.scheitlin.alex.build.model.Project(
                    name,
                    buildConfigurations
            );

            projects.add(project);
        }

        return projects;
    }

    /**
     * Gets team city build configurations as build configurations of the build server model.
     *
     * @param teamCity the team city instance to access the team city rest api client
     * @param project  the project to map its build configurations to the build configuration model
     * @return a list of build configurations of the build configuration model
     */
    private static List<ch.scheitlin.alex.build.model.BuildConfiguration> getBuildConfigurations(TeamCityInstance teamCity, Project project) {
        // fetch build configurations from team city
        List<BuildConfiguration> teamCityBuildConfigurations = Common.getBuildConfigurationsOfProject(teamCity, project.getId());

        // map all team city build configurations to the build configuration model
        List<ch.scheitlin.alex.build.model.BuildConfiguration> buildConfigurations = new ArrayList<ch.scheitlin.alex.build.model.BuildConfiguration>();
        for (BuildConfiguration teamCityBuildConfiguration : teamCityBuildConfigurations) {
            // get all components for the build configuration model
            String name = teamCityBuildConfiguration.getName();
            List<ch.scheitlin.alex.build.model.Branch> branches = getBranches(teamCity, teamCityBuildConfiguration);

            // create the build configuration model
            ch.scheitlin.alex.build.model.BuildConfiguration buildConfiguration = new ch.scheitlin.alex.build.model.BuildConfiguration(
                    name,
                    branches
            );

            buildConfigurations.add(buildConfiguration);
        }

        return buildConfigurations;
    }

    /**
     * Gets team city builds grouped by branches as branches of the build server model.
     *
     * @param teamCity           the team city instance to access the team city rest api client
     * @param buildConfiguration the build configuration to map its branches and builds to the branch and build model
     * @return a list of branches of the branch model
     */
    private static List<ch.scheitlin.alex.build.model.Branch> getBranches(TeamCityInstance teamCity, BuildConfiguration buildConfiguration) {
        // fetch builds from team city
        List<Build> builds = Common.getBuildsOfBuildConfiguration(teamCity, buildConfiguration.getId());

        // get a list of all branch names
        List<String> branchNames = getDistinctBranchNames(builds);

        // group the builds by branch
        List<ch.scheitlin.alex.build.model.Branch> buildsByBranches = new ArrayList<ch.scheitlin.alex.build.model.Branch>(branchNames.size());
        for (int i = 0; i < branchNames.size(); i++) {
            List<ch.scheitlin.alex.build.model.Build> buildsOfThisBranch = new ArrayList<ch.scheitlin.alex.build.model.Build>();

            String branch = branchNames.get(i);
            for (Build build : builds) {
                String thisBranch = build.getBranch().getName();

                // add the build to the list if the branch matches
                // consider branches being null (only the default branch is specified in team city)
                if ((branch != null && branch.equals(thisBranch)) || (branch == null && thisBranch == null)) {
                    buildsOfThisBranch.add(mapTeamcityBuildToBuildModel(teamCity, build));
                }
            }

            buildsByBranches.add(i, new ch.scheitlin.alex.build.model.Branch(branch, buildsOfThisBranch));
        }

        return buildsByBranches;
    }

    /**
     * Maps a TeamCity build to the build model.
     *
     * @param teamCity      the teamcity instance to access the api
     * @param teamcityBuild the teamcity built to map to the build model
     * @return a build model
     */
    private static ch.scheitlin.alex.build.model.Build mapTeamcityBuildToBuildModel(TeamCityInstance teamCity, Build teamcityBuild) {
        String buildId = teamcityBuild.getId().getStringId();
        String buildNumber = teamcityBuild.getBuildNumber();
        boolean status = teamcityBuild.getStatus().toString().toLowerCase().equals("success");
        String statusText = Builds.getStatusText(teamCity, teamcityBuild.getId());
        String repository = Builds.getVscRootUrl(teamCity, teamcityBuild.getId());
        String buildBranch = Builds.getBranchName(teamCity, teamcityBuild.getId());
        String commit = Builds.getCommitHash(teamCity, teamcityBuild.getId());
        return new ch.scheitlin.alex.build.model.Build(
                buildId,
                buildNumber,
                status,
                statusText,
                repository,
                buildBranch,
                commit
        );
    }

    /**
     * Gets all distinct branch names out of a list of builds.
     *
     * @param builds the list of builds to get the branch names from
     * @return a list of distinct branch names
     */
    private static List<String> getDistinctBranchNames(List<Build> builds) {
        List<String> branchNames = new ArrayList<String>();

        // iterate over the list of builds and add the branch name to
        // the list if it is not already contained in this list
        for (Build build : builds) {
            String branchName = build.getBranch().getName();
            if (!branchNames.contains(branchName)) {
                branchNames.add(branchName);
            }
        }

        return branchNames;
    }
}
