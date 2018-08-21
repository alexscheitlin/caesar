import ch.scheitlin.alex.teamcity.TeamCityBuildLogParser;
import ch.scheitlin.alex.teamcity.TeamCityBuild;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String logToParse = "team-city-build-log-5";
        String path = "C:\\Users\\Alex\\Desktop\\";

        String log = null;
        try {
            log = readResourceFile(logToParse + ".txt");
        } catch (Exception ex) {
            System.out.println("Team City build log could not be read!");
            return;
        }

        TeamCityBuild build = null;
        try {
            build = new TeamCityBuildLogParser().parseBuildLog(log);
        } catch (Exception ex) {
            System.out.println("Team City build log could not be parsed!");
            return;
        }

        String mavenLog = build.getMavenLog();

        File file = new File(path + logToParse + "-maven-log.txt");

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (Exception ex) {
            return;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

        try {
            for (String line : mavenLog.split("\n")) {
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

        System.out.println("Maven log saved to: " + file.getAbsolutePath());
    }

    private static String readResourceFile(String fileName) throws Exception {
        StringBuilder result = new StringBuilder();
        InputStream in = Main.class.getResourceAsStream("/" + fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(in);
        } catch (Exception ext) {
            throw new Exception(ext.getMessage());
        }

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }

        scanner.close();

        return result.toString();
    }
}
