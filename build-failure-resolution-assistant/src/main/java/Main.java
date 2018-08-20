import ch.scheitlin.alex.build.Assistant;
import ch.scheitlin.alex.build.model.*;
import ch.scheitlin.alex.build.model.Error;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String host = "http://localhost";
        String username = "alex";
        String password = "scheitlin";
        //String projectPath = "C:\\Users\\Alex\\IdeaProjects\\demo-bank-account";
        String projectPath = "C:\\Users\\alex\\IdeaProjects\\demo-bank-account";

        // login to build server
        BuildServerType type = BuildServerType.TEAM_CITY;
        Assistant assistant = new Assistant(type);
        if (!assistant.connect(host, username, password)) {
            System.out.println("Could not connect to the TeamCity server.");
            return;
        }
        System.out.println("Status: " + (assistant.isConnected() ? "logged in" : "logged out"));

        // get team city information about projects, build configurations, branches, and builds
        BuildServer information = assistant.getBuildServerInformation();

        // list all projects configured on teamcity
        List<String> projectNames = information.getProjectNames();
        System.out.println("Projects:");
        for (int i = 0; i < projectNames.size(); i++) {
            System.out.println("\t" + "[" + (i + 1) + "] " + projectNames.get(i));
        }

        // choose a project
        System.out.print("Select a project by entering the corresponding number: ");
        int projectNameIndex = 1;
        System.out.println(projectNameIndex);
        String projectName = projectNames.get(projectNameIndex - 1);

        // list all builds
        Project project = information.getProject(projectName);
        System.out.println();
        System.out.println(projectName);
        int counter = 0;
        for (BuildConfiguration config : project.getBuildConfigurations()) {
            System.out.println("\t" + config.getName());

            for (Branch branch : config.getBranches()) {
                System.out.println("\t\t" + (branch.getName() == null ? "default" : branch.getName()));

                for (Build build : branch.getBuilds()) {
                    System.out.println("\t\t\t" + "[" + ++counter + "] " + build.getNumber() + " - " + (build.getStatus() ? "success" : "failure"));
                }
            }
        }

        // choose a build
        System.out.print("Select a build by entering the corresponding number: ");
        int buildIndex = 1;
        System.out.println(buildIndex);
        Build selectedBuild = null;
        counter = 0;
        for (BuildConfiguration config : project.getBuildConfigurations()) {
            for (Branch branch : config.getBranches()) {
                for (Build build : branch.getBuilds()) {
                    if (++counter == buildIndex) {
                        selectedBuild = build;

                        System.out.println();
                        System.out.println("Build id:\t\t\t" + selectedBuild.getId());
                        System.out.println("Build number:\t\t" + selectedBuild.getNumber());
                        System.out.println("Build status:\t\t" + (selectedBuild.getStatus()?"success":"failure"));
                        System.out.println("Build status text:\t" + selectedBuild.getStatusText());
                        System.out.println("Repository:\t\t\t" + selectedBuild.getRepository());
                        System.out.println("Branch:\t\t\t\t" + selectedBuild.getBranch());
                        System.out.println("Commit:\t\t\t\t" + selectedBuild.getCommit());
                    }
                }
            }
        }

        // download build log
        if (!assistant.download(selectedBuild)) {
            System.out.println("Could not download the build log of the specified build!");
            return;
        }

        // process build log
        if (!assistant.process()) {
            System.out.println("Could not process the downloaded build log!");
            return;
        }

        // show build information to the user
        System.out.println("Build Status:\t" + assistant.mavenBuild.getStatus());
        if (assistant.mavenBuild.getFailedGoal() != null) {
            System.out.println("Failed Goal:\t" + assistant.mavenBuild.getFailedGoal());
        } else {
            System.out.println("Failed Goal:\t" + "No failed goal detected!");
        }
        if (assistant.mavenBuild.getErrorMessage() != null) {
            System.out.println("Error Message:\t" + assistant.mavenBuild.getErrorMessage());
        } else {
            System.out.println("Error Message:\t" + "No error message detected!");
        }
        System.out.println("Failure Category:\t" + assistant.failureCategory);
        List<Error> errors = assistant.errors;
        if (errors != null) {
            for (Error error : errors) {
                System.out.println("Error:\t\t\t" + error.getMessage());
                System.out.println("\tPath:\t\t\t" + error.getPath());
                System.out.println("\tFile:\t\t\t" + error.getFile());
                System.out.println("\tLine:\t\t\t" + error.getLine());
                System.out.println("\tColumn:\t\t\t" + error.getColumn());
            }
        }

        // ask user to fix build
        System.out.println("Do you want to checkout the code of the broken build and start fixing the error?");
        System.out.println("Your uncommitted changes will be saved automatically!");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        System.out.print("Select your answer: ");

        int answer = 1;
        System.out.println(answer);

        // fix build
        if (answer == 1) {
            if (!assistant.fix(projectPath)) {
                return;
            }

            // stop fixing build
            System.out.println("Enter '1' once you are finished fixing the build.");
            System.out.println("If there are saved and uncommitted changes they will be applied automatically.");
            System.out.println("1");

            assistant.finish();
        }


        // logout from teamcity
        assistant.disconnect();
        System.out.println();
        System.out.println("Status: " + (assistant.isConnected() ? "logged in" : "logged out"));
    }
}
