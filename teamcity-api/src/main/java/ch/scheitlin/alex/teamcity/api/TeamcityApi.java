package ch.scheitlin.alex.teamcity.api;

import ch.scheitlin.alex.build.model.BuildServer;
import org.jetbrains.teamcity.rest.BuildId;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;

import java.io.File;
import java.util.Scanner;

public class TeamcityApi {
    private TeamCityInstance teamCity;

    public TeamcityApi(String host, String username, String password) {
        this.teamCity = TeamCityInstanceFactory.httpAuth(host, username, password);
    }

    public BuildServer getBuildServerInformation() {
        return Specific.getBuildServerModel(this.teamCity);
    }

    public static boolean testTeamCityConnection(String host, String username, String password) {
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

    public String getBuildLog(String buildId) throws Exception {
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

    private String readFile(File file) throws Exception {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        return result.toString();
    }
}
