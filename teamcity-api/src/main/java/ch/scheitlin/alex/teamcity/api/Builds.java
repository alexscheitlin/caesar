package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.BuildId;
import org.jetbrains.teamcity.rest.Revision;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.VcsRoot;

import java.util.List;

class Builds {
    static String getStatusText(TeamCityInstance teamCity, BuildId buildId) {
        return teamCity.build(buildId).fetchStatusText();
    }

    static String getVscRootUrl(TeamCityInstance teamCity, BuildId buildId) {
        Revision latestRevision = getLatestRevision(teamCity, buildId);
        VcsRoot vcsRoot = Common.getVcsRootOfRevision(teamCity, latestRevision);

        return vcsRoot.getUrl();
    }

    static String getBranchName(TeamCityInstance teamCity, BuildId buildId) {
        Revision latestRevision = getLatestRevision(teamCity, buildId);

        return latestRevision.getVcsBranchName();
    }

    static String getCommitHash(TeamCityInstance teamCity, BuildId buildId) {
        Revision latestRevision = Builds.getLatestRevision(teamCity, buildId);

        return latestRevision.getVersion();
    }

    private static Revision getLatestRevision(TeamCityInstance teamCity, BuildId buildId) {
        List<Revision> revisions = teamCity.build(buildId).fetchRevisions();

        return revisions.get(0);
    }
}
