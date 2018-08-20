package ch.scheitlin.alex.teamcity;

import ch.scheitlin.alex.build.Parser;
import ch.scheitlin.alex.build.model.BuildServerType;
import ch.scheitlin.alex.teamcity.config.BuildLogConfig;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamCityBuildLogParser extends Parser {

    public TeamCityBuildLogParser() {
        // set build server type
        super(BuildServerType.TEAM_CITY);
    }

    /**
     * Parse a build log line by line.
     *
     * @param log the log ot parse
     * @return a build log with the original log and all extracted information
     * @throws Exception if the log can not be parsed
     */
    public TeamCityBuild parseBuildLog(String log) throws Exception {
        // read regular expressions from the configuration file for parsing the log lines
        BuildLogConfig config = BuildLogConfig.readConfig();
        RegexMatcher headerLine1_1 = new RegexMatcher(config.getRegex().get("headerLine11"));
        RegexMatcher headerLine1_2 = new RegexMatcher(config.getRegex().get("headerLine12"));
        RegexMatcher headerLine2 = new RegexMatcher(config.getRegex().get("headerLine2"));
        RegexMatcher headerLine3 = new RegexMatcher(config.getRegex().get("headerLine3"));
        RegexMatcher headerLine4 = new RegexMatcher(config.getRegex().get("headerLine4"));
        RegexMatcher headerLine6 = new RegexMatcher(config.getRegex().get("headerLine6"));
        RegexMatcher logEntry = new RegexMatcher(config.getRegex().get("logEntry"));

        // create a new build log to fill and return
        TeamCityBuild teamCityBuild = new TeamCityBuild();

        // always keep track of the last added entry if one entry has multiple lines
        TeamCityBuildLogEntry previousEntry = null;

        // parse the log line by line
        int currentLine = 0;
        String[] logLines = log.split("\n");
        for (String line : logLines) {
            currentLine++;

            if (logEntry.matches(line)) {
                // treat the current line as a log entry

                // parse all log entry values
                Object[] components = TeamCityBuildLogParser.parseLogEntry(logEntry, line);

                int lineNumber = currentLine;
                String time = (String) components[0];
                TeamCityBuildLogEntrySeverity severity = (TeamCityBuildLogEntrySeverity) components[1];
                int level = (Integer) components[2];
                String message = (String) components[3];

                TeamCityBuildLogEntry entry = new TeamCityBuildLogEntry(lineNumber, time, severity, level, message);
                teamCityBuild.addBuildLogEntry(entry);

                // keep track of previous entry
                previousEntry = entry;

            } else if (headerLine1_1.matches(line)) {
                // parse header line 1
                String[] components = TeamCityBuildLogParser.parseHeaderLine1_1(headerLine1_1, line);
                teamCityBuild.setProjectName(components[0]);
                teamCityBuild.setBuildConfigurationName(components[1]);
                teamCityBuild.setNumber(components[2]);
                teamCityBuild.setBranchName(components[3]);

            } else if (headerLine1_2.matches(line)) {
                // parse header line 1
                String[] components = TeamCityBuildLogParser.parseHeaderLine1_2(headerLine1_2, line);
                teamCityBuild.setProjectName(components[0]);
                teamCityBuild.setBuildConfigurationName(components[1]);
                teamCityBuild.setNumber(components[2]);
                teamCityBuild.setBranchName("default");

            } else if (headerLine2.matches(line)) {
                // parse header line 2
                teamCityBuild.setStartDate(TeamCityBuildLogParser.parseHeaderLine2(headerLine2, line));

            } else if (headerLine3.matches(line)) {
                // parse header line 3
                Object[] components = TeamCityBuildLogParser.parseHeaderLine3(headerLine3, line);
                teamCityBuild.setFinishDate((Date) components[0]);
                teamCityBuild.setStatus((TeamCityBuildStatus) components[1]);
                teamCityBuild.setStatusText((String) components[2]);

            } else if (headerLine4.matches(line)) {
                // parse header line 4
                String[] components = TeamCityBuildLogParser.parseHeaderLine4(headerLine4, line);
                teamCityBuild.setVcsRootName(components[0]);
                teamCityBuild.setCommitHash(components[1]);

            } else if (headerLine6.matches(line)) {
                // parse header line 6
                teamCityBuild.setTeamCityServerVersion(TeamCityBuildLogParser.parseHeaderLine6(headerLine6, line));

            } else {
                // if the loop reaches this line, one of the following two cases happened:
                // - the current line is a header line not being needed (previousEntry == null)
                // - the current line is a log line that belongs to the previous log entry (previousEntry != null)

                // append information to the previous log entry if it is available
                // if it is not (case 1) this line can be omitted
                if (previousEntry != null) {
                    // remove trailing white spaces
                    previousEntry.appendMessageLine(line.replaceFirst("\\s++$", ""));
                } else {
                    //System.out.println("Skipped a line: " + currentLine);
                    //System.out.println("\t" + line);
                }
            }
        }

        // parse build steps
        teamCityBuild.setBuildSteps(TeamCityBuildLogParser.extractBuildSteps(teamCityBuild.getBuildLogEntries()));

        return teamCityBuild;
    }

    /**
     * Parses a build log line to extract the following components:
     * - the name of the project
     * - the name of the build configuration
     * - the number of the build
     * - the branch name
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static String[] parseHeaderLine1_1(RegexMatcher regex, String line) throws Exception {
        return regex.extractComponents(line);
    }

    /**
     * Parses a build log line to extract the following components:
     * - the name of the project
     * - the name of the build configuration
     * - the number of the build
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static String[] parseHeaderLine1_2(RegexMatcher regex, String line) throws Exception {
        return regex.extractComponents(line);
    }

    /**
     * Parses a build log line to extract the following components:
     * - the start date and time of the build
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static Date parseHeaderLine2(RegexMatcher regex, String line) throws Exception {
        return convertStringToDate(regex.extractComponents(line)[0]);
    }

    /**
     * Parses a build log line to extract the following components:
     * - the finish date date and time of the build
     * - the status of the build
     * - the status text of the build
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static Object[] parseHeaderLine3(RegexMatcher regex, String line) throws Exception {
        String[] components = regex.extractComponents(line);

        Date finishDate = convertStringToDate(components[0]);
        TeamCityBuildStatus teamCityBuildStatus = TeamCityBuildStatus.valueOf(components[1]);
        String buildStatusText = components[2];

        return new Object[]{finishDate, teamCityBuildStatus, buildStatusText};
    }

    /**
     * Parses a build log line to extract the following components:
     * - the vcs root (url and branch)
     * - the vcs version (commit hash)
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static String[] parseHeaderLine4(RegexMatcher regex, String line) throws Exception {
        return regex.extractComponents(line);
    }

    /**
     * Parses a build log line to extract the following components:
     * - the team city server version
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return the specified components
     * @throws Exception if the line does not match the regular expression
     */
    private static String parseHeaderLine6(RegexMatcher regex, String line) throws Exception {
        return regex.extractComponents(line)[0];
    }

    /**
     * Parses a build log line to extract the following components:
     * - the time
     * - the severity
     * - the level
     * - the message
     *
     * @param regex the RegexMatcher to match the pattern to the log line and extract its components
     * @param line  the log line to parse
     * @return a TeamCityBuildLogEntry
     * @throws Exception if the line does not match the regular expression
     */
    private static Object[] parseLogEntry(RegexMatcher regex, String line) throws Exception {
        // match pattern to log line
        String[] components = regex.extractComponents(line);

        String time = components[0];

        TeamCityBuildLogEntrySeverity severity = TeamCityBuildLogEntrySeverity.fromString(components[1]);

        int level = components[2].length();

        String message = components[3];

        // return the parsed information
        return new Object[]{time, severity, level, message};
    }

    /**
     * Converts a date as String of format yyyy-MM-dd HH:mm:ss to a Date.
     *
     * @param dateString the String to convert
     * @return the Date object
     */
    private static Date convertStringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.print(e.getMessage());
        }

        return date;
    }

    private static List<TeamCityBuildStep> extractBuildSteps(List<TeamCityBuildLogEntry> logEntries) throws Exception {
        // read regular expressions from the configuration file
        BuildLogConfig config = BuildLogConfig.readConfig();
        RegexMatcher buildStep1Regex = new RegexMatcher(config.getRegex().get("buildStep1"));
        RegexMatcher buildStep2Regex = new RegexMatcher(config.getRegex().get("buildStep2"));
        String regexTempl = config.getRegex().get("buildStepExitCodeTempl");

        // get build steps
        int buildStepLevel = 0;
        List<TeamCityBuildStep> teamCityBuildSteps = new ArrayList<TeamCityBuildStep>();

        TeamCityBuildStep teamCityBuildStep = null;

        for (TeamCityBuildLogEntry entry : logEntries) {
            // try to catch a new build step
            if (entry.getLevel() == buildStepLevel) {
                // finish last build step
                if (teamCityBuildStep != null) {
                    teamCityBuildSteps.add(teamCityBuildStep);
                    teamCityBuildStep = null;
                }

                // try to catch new build step
                if (buildStep2Regex.matches(entry.getMessage())) {
                    String[] components = buildStep2Regex.extractComponents(entry.getMessage());

                    int number = Integer.valueOf(components[0]);
                    String name = components[1];
                    TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.fromString(components[2]);
                    String duration = components[3];

                    teamCityBuildStep = new TeamCityBuildStep(number, name, runnerType, duration);

                } else if (buildStep1Regex.matches(entry.getMessage())) {
                    String[] components = buildStep1Regex.extractComponents(entry.getMessage());

                    int number = Integer.valueOf(components[0]);
                    String name = components[1];
                    TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.fromString(components[1]);
                    String duration = components[2];
                    teamCityBuildStep = new TeamCityBuildStep(number, name, runnerType, duration);
                }
            } else {
                // add entries to build step
                if (teamCityBuildStep != null) {
                    teamCityBuildStep.addBuildLogEntry(entry);

                    // try to find exit code
                    String value = String.valueOf(teamCityBuildStep.getNumber());
                    String regex = String.format(regexTempl, value);
                    RegexMatcher buildStepExitCodeRegex = new RegexMatcher(regex);

                    if (entry.getLevel() == buildStepLevel + 1) {
                        if (buildStepExitCodeRegex.matches(entry.getMessage())) {
                            String[] components2 = buildStepExitCodeRegex.extractComponents(entry.getMessage());
                            teamCityBuildStep.setExitCode(Integer.valueOf(components2[0]));
                        }
                    }
                }
            }
        }

        return teamCityBuildSteps;
    }
}
