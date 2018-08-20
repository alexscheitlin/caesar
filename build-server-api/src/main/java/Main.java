import ch.scheitlin.alex.build.BuildServerApi;
import ch.scheitlin.alex.build.model.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String host = "http://localhost";
        String username = "alex";
        String password = "scheitlin";
        String path = "C:\\Users\\Alex\\Desktop\\";

        BuildServerType type = BuildServerType.TEAM_CITY;
        BuildServerApi api = new BuildServerApi(type);
        api.login(host, username, password);

        BuildServer information = api.toBuildServerModel();

        int counter = 0;
        for (BuildServerProject project : information.getProjects()) {
            System.out.println(project.getName());

            for (BuildServerBuildConfiguration buildConfiguration : project.getBuildConfigurations()) {
                System.out.println("\t" + buildConfiguration.getName());

                for (BuildServerBranch branch : buildConfiguration.getBranches()) {
                    System.out.println("\t\t" + branch.getName());

                    for (BuildServerBuild build : branch.getBuilds()) {
                        System.out.println("\t\t\t" + "(" + ++counter + ") " + build.getStatus());
                    }
                }
            }
        }

        System.out.println();
        System.out.print("Please enter the number of the build to download the team city build log: ");

        int number = -1;

        try {
            Scanner in = new Scanner(System.in);
            number = in.nextInt();
        } catch (Exception ex) {
            return;
        }

        System.out.println("Download: " + number);

        counter = 0;
        for (BuildServerProject project : information.getProjects()) {
            for (BuildServerBuildConfiguration buildConfiguration : project.getBuildConfigurations()) {
                for (BuildServerBranch branch : buildConfiguration.getBranches()) {
                    for (BuildServerBuild build : branch.getBuilds()) {
                        if (++counter == number) {
                            try {
                                File file = new File(path + build.getId() + ".txt");
                                api.saveBuildLogTo(build.getId(), file);
                                System.out.println("Downloaded: " + number);

                                return;
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                                return;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Not found!");
    }
}
