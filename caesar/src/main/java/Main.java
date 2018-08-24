import ch.scheitlin.alex.build.Caesar;
import ch.scheitlin.alex.build.model.*;
import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.maven.MavenBuild;
import ch.scheitlin.alex.maven.MavenBuildStatus;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BuildServerType BUILD_SERVER_TYPE = BuildServerType.TEAM_CITY;
    private static final String HOST = "http://localhost";
    private static final String USERNAME = "alex";
    private static final String PASSWORD = "scheitlin";

    // the local path to the project to fix a broken build (to access the git repository)
    private static final String PATH_TO_PROJECT = "C:\\Users\\alex\\IdeaProjects\\demo-bank-account";

    // to run through the script without interacting with it
    private static final boolean AUTO_RUN = false;
    private static final int AUTO_RUN_PROJECT_INDEX = 1;
    private static final int AUTO_RUN_BUILD_INDEX = 12;

    public static void main(String[] args) {
        System.out.println(getCaesarTitle());
        Caesar caesar = new Caesar(BUILD_SERVER_TYPE);

        // connect to build server
        System.out.println();
        System.out.println("Connecting to build server...");
        if (!caesar.connect(HOST, USERNAME, PASSWORD)) {
            System.out.println("\t failed");
            exit();
            return;
        }
        System.out.println("\tconnected");

        // get team city information about projects, build configurations, branches, and builds
        System.out.println();
        System.out.println("Fetch build server data...");
        BuildServer buildServerData = caesar.getBuildServerInformation();
        if (buildServerData == null) {
            System.out.println("\t failed");
            exit();
            return;
        }
        System.out.println("\tfetched");

        // list all projects configured on the build server
        System.out.println();
        List<String> projectNames = buildServerData.getProjectNames();
        System.out.println("Configured projects on the build server:");
        for (int i = 0; i < projectNames.size(); i++) {
            System.out.println("\t" + "[" + (i + 1) + "] " + projectNames.get(i));
        }

        // select a project
        int projectIndex = 0;
        if (!AUTO_RUN) {
            System.out.println();
            System.out.print("Please select a project by entering the corresponding number: ");
            try {
                projectIndex = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("\tCould not read your input!");
                exit();
                return;
            }
        } else {
            projectIndex = AUTO_RUN_PROJECT_INDEX;
        }
        String projectName = projectNames.get(projectIndex - 1);
        BuildServerProject selectedProject = buildServerData.getProject(projectName);

        // list all builds of the selected project
        System.out.println();
        System.out.println(selectedProject.getName());
        int counter = 0;
        for (BuildServerBuildConfiguration config : selectedProject.getBuildConfigurations()) {
            System.out.println("\t" + config.getName());

            for (BuildServerBranch branch : config.getBranches()) {
                System.out.println("\t\t" + (branch.getName() == null ? "default" : branch.getName()));

                for (BuildServerBuild build : branch.getBuilds()) {
                    System.out.println("\t\t\t" +
                            String.format("%4s", "[" + ++counter + "]") + " " +
                            (build.getStatus() ? "success" : "failure") +
                            " (" + build.getStatusText() + ")"
                    );
                }
                System.out.println();
            }
        }

        // select a build
        int buildIndex = 0;
        if (!AUTO_RUN) {
            System.out.println();
            System.out.print("Please select a build by entering the corresponding number: ");
            try {
                buildIndex = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("\tCould not read your input!");
                exit();
                return;
            }
        } else {
            buildIndex = AUTO_RUN_BUILD_INDEX;
        }
        BuildServerBuild selectedBuild = null;
        counter = 0;
        for (BuildServerBuildConfiguration config : selectedProject.getBuildConfigurations()) {
            for (BuildServerBranch branch : config.getBranches()) {
                for (BuildServerBuild build : branch.getBuilds()) {
                    if (++counter == buildIndex) {
                        selectedBuild = build;

                        System.out.println();
                        System.out.println("BuildServerBuild id:\t\t\t" + selectedBuild.getId());
                        System.out.println("BuildServerBuild number:\t\t" + selectedBuild.getNumber());
                        System.out.println("BuildServerBuild status:\t\t" + (selectedBuild.getStatus() ? "success" : "failure"));
                        System.out.println("BuildServerBuild status text:\t" + selectedBuild.getStatusText());
                        System.out.println("Repository:\t\t\t\t\t\t" + selectedBuild.getRepository());
                        System.out.println("BuildServerBranch:\t\t\t\t" + selectedBuild.getBranch());
                        System.out.println("Commit:\t\t\t\t\t\t\t" + selectedBuild.getCommit());
                    }
                }
            }
        }

        // download build log
        System.out.println();
        System.out.println("Downloading build log...");
        if (!caesar.download(selectedBuild)) {
            System.out.println("\t failed");
            exit();
            return;
        }
        System.out.println("\tdownloaded");

        // process build log
        System.out.println();
        System.out.println("Processing build log...");
        if (!caesar.process()) {
            System.out.println("\t failed");
            exit();
            return;
        }
        System.out.println("\tprocessed");

        // show build information to the user
        MavenBuild mavenBuild = caesar.getMavenBuild();
        System.out.println();
        System.out.println("Build Status:\t\t\t\t" + mavenBuild.getStatus());

        if (mavenBuild.getStatus() == MavenBuildStatus.SUCCESS) {
            exit();
            return;
        }

        System.out.print("Failed Goal:\t\t\t\t");
        if (mavenBuild.getFailedGoal() != null) {
            System.out.println(mavenBuild.getFailedGoal());
        } else {
            System.out.println("No failed goal detected!");
        }
        System.out.print("Error Message:\t\t\t\t");
        if (mavenBuild.getErrorMessage() != null) {
            System.out.println(mavenBuild.getErrorMessage());
        } else {
            System.out.println("No error message detected!");
        }
        System.out.println("Failure Category:\t\t\t" + caesar.getFailureCategory());

        // show errors to the user
        System.out.println();
        List<Error> errors = caesar.getErrors();
        if (errors != null) {
            for (Error error : errors) {
                System.out.println("Error:\t\t" + error.getMessage());
                System.out.println("\tPath:\t" + error.getPath());
                System.out.println("\tFile:\t" + error.getFile());
                System.out.println("\tLine:\t" + error.getLine());
                System.out.println("\tColumn:\t" + error.getColumn());
            }
        }

        System.out.println();
        System.out.println("You may explore the errors in your local code. However, be aware that your local code may" +
                " be different from the version that failed on the build server.");

        System.out.println();
        System.out.println("[1] View the code that caused the build break. Uncommitted changes will be stashed" +
                " automatically.");
        System.out.println("[2] Exit CAESAR.");

        System.out.println();
        System.out.print("Please enter a number to continue: ");

        // get choice
        int choice = 0;
        if (!AUTO_RUN) {
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("\tCould not read your input!");
                exit();
                return;
            }
        } else {
            exit();
            return;
        }
        if (choice != 1) {
            exit();
            return;
        }

        // check out broken code
        System.out.println();
        System.out.println("Loading broken code...");
        if (!caesar.fix(PATH_TO_PROJECT)) {
            System.out.println("\t failed");
            exit();
            return;
        }
        System.out.println("\tloaded to new branch: " + caesar.getNewBranch());
        System.out.println("\tchecked out to new branch");
        if (caesar.getStashedChanges() != null) {
            System.out.println("\topen changes stashed: " + caesar.getStashedChanges());
        }


        System.out.println();
        System.out.print("Please press enter after you are finished fixing the code.");

        try {
            new Scanner(System.in).nextLine();
        } catch (Exception ex) {
            System.out.println("\tCould not read your input!");
        }

        // logout from the build server
        caesar.disconnect();
        System.out.println();
        System.out.println("Status: " + (caesar.isConnected() ? "logged in" : "logged out"));

        exit();
    }

    private static void exit() {
        System.out.println();
        System.out.println("Exit Caesar!");
    }

    private static String getCaesarTitle() {
        return "  _____          ______  _____         _____  \n" +
                " / ____|   /\\   |  ____|/ ____|  /\\   |  __ \\ \n" +
                "| |       /  \\  | |__  | (___   /  \\  | |__) |\n" +
                "| |      / /\\ \\ |  __|  \\___ \\ / /\\ \\ |  _  / \n" +
                "| |____ / ____ \\| |____ ____) / ____ \\| | \\ \\ \n" +
                " \\_____/_/    \\_\\______|_____/_/    \\_\\_|  \\_\\";
    }
}
