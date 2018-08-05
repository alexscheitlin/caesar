import ch.scheitlin.alex.build.model.*;
import ch.scheitlin.alex.teamcity.api.TeamcityApi;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String host = "http://localhost";
        String username = "alex";
        String password = "scheitlin";
        String path = "C:\\Users\\Alex\\Desktop\\";

        TeamcityApi api = new TeamcityApi(host, username, password);

        BuildServer information = api.getBuildServerInformation();

        int counter = 0;
        for (Project project : information.getProjects()) {
            System.out.println(project.getName());

            for (BuildConfiguration buildConfiguration : project.getBuildConfigurations()) {
                System.out.println("\t" + buildConfiguration.getName());

                for (Branch branch : buildConfiguration.getBranches()) {
                    System.out.println("\t\t" + branch.getName());

                    for (Build build : branch.getBuilds()) {
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
        for (Project project : information.getProjects()) {
            for (BuildConfiguration buildConfiguration : project.getBuildConfigurations()) {
                for (Branch branch : buildConfiguration.getBranches()) {
                    for (Build build : branch.getBuilds()) {
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
