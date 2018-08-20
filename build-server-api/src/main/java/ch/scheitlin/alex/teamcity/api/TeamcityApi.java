package ch.scheitlin.alex.teamcity.api;

import ch.scheitlin.alex.build.Api;
import ch.scheitlin.alex.build.model.BuildServer;
import ch.scheitlin.alex.build.model.BuildServerType;
import org.jetbrains.teamcity.rest.BuildId;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;

import java.io.File;

public class TeamcityApi extends Api {
    private TeamCityInstance teamCity;

    public TeamcityApi() {
        // set build server type
        super(BuildServerType.TEAM_CITY);
    }

    public boolean testConnection(String host, String username, String password) {
        if (host == null || username == null || password == null) {
            return false;
        }

        TeamCityInstance teamCity;
        try {
            teamCity = TeamCityInstanceFactory.httpAuth(host, username, password);
            teamCity.rootProject();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void login(String host, String username, String password) {
        this.teamCity = TeamCityInstanceFactory.httpAuth(host, username, password);
    }

    public BuildServer toBuildServerModel() {
        return Specific.getBuildServerModel(this.teamCity);
    }

    public String downloadBuildLog(String buildId) throws Exception {
        // create temp file
        File file = File.createTempFile("log", ".txt");

        // download build log to temp file
        saveBuildLogTo(buildId, file);

        // read file to string
        String buildLog = readFile(file);
        file.delete();

        return buildLog;
    }

    public void saveBuildLogTo(String buildId, File file) throws Exception {
        // download build log to temp file
        this.teamCity.build(new BuildId(buildId)).downloadBuildLog(file);
    }

    public void logout() {
        this.teamCity = null;
    }
}
