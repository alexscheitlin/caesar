package ch.scheitlin.alex.teamcity;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String logToParse = "team-city-build-log-1";
        String path = "C:\\Users\\Alex\\Desktop\\";

        String log = readResourceFile(logToParse + ".log");

        TeamCityBuild build = null;
        try {
            build = BuildLogParser.parseBuildLog(log);
        } catch (Exception ex) {
            return;
        }

        String mavenLog = build.getMavenLog();

        File file = new File(path + logToParse +"-maven-log.txt");

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
