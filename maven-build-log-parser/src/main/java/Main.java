import ch.scheitlin.alex.maven.MavenBuild;
import ch.scheitlin.alex.maven.MavenBuildLogParser;
import ch.scheitlin.alex.maven.MavenGoal;
import ch.scheitlin.alex.maven.MavenModule;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String logToParse = "team-city-build-log-1-maven-log";
        String path = "C:\\Users\\Alex\\Desktop\\";

        String log = readResourceFile(logToParse + ".txt");

        MavenBuild mavenBuild = MavenBuildLogParser.parse(log);

        // print modules
        int moduleCounter = 0;
        for (MavenModule module : mavenBuild.getModules()) {
            System.out.println(++moduleCounter + ": " + module.getName() + " (" + module.getStatus() + ")");
        }

        // select module
        int moduleNumber = -1;
        if (mavenBuild.getModules().size() != 1) {
            System.out.print("Please enter the number of the module you want to list the goals: ");
            try {
                Scanner in = new Scanner(System.in);
                moduleNumber = in.nextInt();
            } catch (Exception ex) {
                System.out.println("Could not read your input!");
                return;
            }
        } else {
            moduleNumber = 1;
        }

        // get selected module
        MavenModule selectedModule = null;
        moduleCounter = 0;
        for (MavenModule module : mavenBuild.getModules()) {
            if (++moduleCounter == moduleNumber) {
                selectedModule = module;
                System.out.println();
                System.out.println("Module: " + module.getName());
            }
        }
        if (selectedModule == null) {
            System.out.println("Module with number " + moduleNumber + " not found!");
            return;
        }

        // print goals of module
        int goalCounter = 0;
        for (MavenGoal goal : selectedModule.getGoals()) {
            System.out.println("\t" + ++goalCounter + ": " + goal.getPlugin() + ":" + goal.getName());
        }

        // select goal
        int goalNumber = -1;
        if (selectedModule.getGoals().size() != 1) {
            System.out.print("Please enter the number of the goal you want to see the log: ");
            try {
                Scanner in = new Scanner(System.in);
                goalNumber = in.nextInt();
            } catch (Exception ex) {
                System.out.println("Could not read your input!");
                return;
            }
        } else {
            goalNumber = 1;
        }

        // get selected goal
        MavenGoal selectedGoal = null;
        goalCounter = 0;
        for (MavenGoal goal : selectedModule.getGoals()) {
            if (++goalCounter == goalNumber) {
                selectedGoal = goal;
                System.out.println();
                System.out.println("Goal: " + goal.getPlugin() + ":" + goal.getName());
            }
        }
        if (selectedGoal == null) {
            System.out.println("Goal with number " + goalNumber + " not found!");
            return;
        }

        // print goal log
        String goalLog = selectedGoal.getLinesAsMultiLineString();
        System.out.println(goalLog);

        // save goal log to file
        File file = new File(path + selectedGoal.getPlugin() + "_" + selectedGoal.getName() + ".txt");

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (Exception ex) {
            return;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

        try {
            for (String line : goalLog.split("\n")) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception ex) {
            //return;
        }

        try {
            writer.close();
        } catch (Exception ex) {
            return;
        }

        System.out.println("Goal log saved to: " + file.getAbsolutePath());
    }

    private static String readResourceFile(String fileName) {
        StringBuilder result = new StringBuilder();
        InputStream in = Main.class.getResourceAsStream("/" + fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(in);
        } catch (Exception ext) {
            return null;
        }

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }

        scanner.close();

        return result.toString();
    }
}
