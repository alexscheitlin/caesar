package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.TeamCityInstance;

import java.util.ArrayList;
import java.util.List;

class Specific {
    /**
     * Gets a build configuration with the builds listed for all distinct branches.
     *
     * @param buildConfigurationName the name of the build configuration
     * @param builds                 the list of builds to extract the builds by branch
     * @return a build configuration
     */
    static ch.scheitlin.alex.build.model.BuildConfiguration getBuildsByBranches(TeamCityInstance teamCity, String buildConfigurationName, List<Build> builds) {
        // get a list of all branch names
        List<String> branchNames = Specific.getDistinctBranchNames(builds);
        List<ch.scheitlin.alex.build.model.Branch> buildsByBranches = new ArrayList<ch.scheitlin.alex.build.model.Branch>(branchNames.size());

        // iterate over the list of builds and create an array list of builds for each branch
        for (int i = 0; i < branchNames.size(); i++) {
            List<ch.scheitlin.alex.build.model.Build> buildsOfThisBranch = new ArrayList<ch.scheitlin.alex.build.model.Build>();

            String branch = branchNames.get(i);
            for (Build build : builds) {
                String thisBranch = build.getBranch().getName();

                // add the build to the list if the branch matches
                // consider branches being null (only the default branch is specified in team city)
                if ((branch != null && branch.equals(thisBranch)) || (branch == null && thisBranch == null)) {
                    String buildId = build.getId().getStringId();
                    String buildNumber = build.getBuildNumber();
                    boolean status = build.getStatus().toString().toLowerCase().equals("success");
                    String statusText = Builds.getStatusText(teamCity, build.getId());
                    String repository = Builds.getVscRootUrl(teamCity, build.getId());
                    String buildBranch = Builds.getBranchName(teamCity, build.getId());
                    String commit = Builds.getCommitHash(teamCity, build.getId());
                    buildsOfThisBranch.add(new ch.scheitlin.alex.build.model.Build(
                            buildId,
                            buildNumber,
                            status,
                            statusText,
                            repository,
                            buildBranch,
                            commit
                    ));
                }
            }

            buildsByBranches.add(i, new ch.scheitlin.alex.build.model.Branch(branch, buildsOfThisBranch));
        }

        return new ch.scheitlin.alex.build.model.BuildConfiguration(buildConfigurationName, buildsByBranches);
    }

    /**
     * Gets all distinct branch names out of a list of builds.
     *
     * @param builds the list of builds to get the branch names from
     * @return a list of distinct branch names
     */
    static List<String> getDistinctBranchNames(List<Build> builds) {
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
