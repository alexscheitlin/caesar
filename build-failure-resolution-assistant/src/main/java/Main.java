import ch.scheitlin.alex.build.Helper;
import ch.scheitlin.alex.build.model.Branch;
import ch.scheitlin.alex.build.model.Build;
import ch.scheitlin.alex.build.model.BuildConfiguration;
import ch.scheitlin.alex.build.model.Error;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String host = "http://localhost";
        String username = "alex";
        String password = "scheitlin";
        //String projectPath = "C:\\Users\\Alex\\IdeaProjects\\demo-bank-account";
        String projectPath = "C:\\Users\\alex\\IdeaProjects\\demo-bank-account";

        // login to teamcity
        Helper helper = new Helper();
        if (!helper.connect(host, username, password)) {
            System.out.println("Could not connect to the TeamCity server.");
            return;
        }
        System.out.println("Status: " + (helper.isConnected() ? "logged in" : "logged out"));

        // list all projects configured on teamcity
        List<String> projectNames = helper.getTeamCityProjectNames();
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
        List<BuildConfiguration> configs = helper.getBuildConfigurationsToShow(projectName);
        System.out.println();
        System.out.println(projectName);
        int counter = 0;
        for (BuildConfiguration config : configs) {
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
        for (BuildConfiguration config : configs) {
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
        if (!helper.download(selectedBuild)) {
            System.out.println("Could not download the build log of the specified build!");
            return;
        }

        // process build log
        if (!helper.process()) {
            System.out.println("Could not process the downloaded build log!");
            return;
        }

        // show build information to the user
        System.out.println("Build Status:\t" + helper.mavenBuild.status);
        if (helper.mavenBuild.failedGoal != null) {
            System.out.println("Failed Goal:\t" + helper.mavenBuild.failedGoal);
        } else {
            System.out.println("Failed Goal:\t" + "No failed goal detected!");
            return;
        }
        System.out.println("Failure Category:\t" + helper.failureCategory);
        List<Error> errors = helper.errors;
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
            if (!helper.fix(projectPath)) {
                return;
            }

            // stop fixing build
            System.out.println("Enter '1' once you are finished fixing the build.");
            System.out.println("If there are saved and uncommitted changes they will be applied automatically.");
            System.out.println("1");

            helper.finish();
        }


        // logout from teamcity
        helper.disconnect();
        System.out.println();
        System.out.println("Status: " + (helper.isConnected() ? "logged in" : "logged out"));
    }
}
