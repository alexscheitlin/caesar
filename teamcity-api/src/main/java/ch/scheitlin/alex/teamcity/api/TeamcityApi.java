package ch.scheitlin.alex.teamcity.api;

import org.jetbrains.teamcity.rest.Build;
import org.jetbrains.teamcity.rest.BuildId;
import org.jetbrains.teamcity.rest.BuildConfiguration;
import org.jetbrains.teamcity.rest.Project;
import org.jetbrains.teamcity.rest.TeamCityInstance;
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamcityApi {
    private TeamCityInstance teamCity;

    public TeamcityApi(String host, String username, String password) {
        this.teamCity = TeamCityInstanceFactory.httpAuth(host, username, password);
    }

    // get all projects configured in team city
    public List<Project> getProjects() {
        return Common.getProjects(this.teamCity);
    }

    public List<ch.scheitlin.alex.build.model.BuildConfiguration> getBuildConfigurationsToShow(Project project) {
        List<BuildConfiguration> buildConfigurations = Common.getBuildConfigurationsOfProject(this.teamCity, project.getId());
        ArrayList<ch.scheitlin.alex.build.model.BuildConfiguration> myBuildConfigurations = new ArrayList<ch.scheitlin.alex.build.model.BuildConfiguration>();

        for (BuildConfiguration buildConfiguration : buildConfigurations) {
            List<Build> builds = Common.getBuildsOfBuildConfiguration(this.teamCity, buildConfiguration.getId());
            ch.scheitlin.alex.build.model.BuildConfiguration myBuildConfiguration = Specific.getBuildsByBranches(
                    this.teamCity,
                    buildConfiguration.getName(),
                    builds
            );

            myBuildConfigurations.add(myBuildConfiguration);
        }

        return myBuildConfigurations;
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
        this.teamCity.build(new BuildId(buildId)).downloadBuildLog(file);

        // read file to string
        String buildLog = readFile(file);
        file.delete();

        return buildLog;
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
