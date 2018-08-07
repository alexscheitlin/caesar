package ch.scheitlin.alex.teamcity;

import ch.scheitlin.alex.utils.RegexMatcher;

public class TeamCityBuildLogEntry {
    private int lineNumber;
    private String time;
    private TeamCityBuildLogEntrySeverity severity;
    private int level;
    private String message;

    public TeamCityBuildLogEntry(int lineNumber, String time, TeamCityBuildLogEntrySeverity severity, int level, String message) {
        this.lineNumber = lineNumber;
        this.time = time;
        this.severity = severity;
        this.level = level;
        this.message = message;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getTime() {
        return this.time;
    }

    public TeamCityBuildLogEntrySeverity getSeverity() {
        return this.severity;
    }

    public int getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public void appendMessageLine(String line) {
        if (this.message == null) {
            this.message = line;
        } else {
            this.message += "\n" + line;
        }
    }

    public String toString() {
        return String.format("%1$" + 3 + "s", this.lineNumber) + ": " +
                this.time + " " +
                toTabs(this.level) +
                this.message;
    }

    public String toOriginalString() {
        return "[" + this.time + "]" +
                this.severity.toString() + ": " +
                toTabs(this.level) +
                this.message;
    }

    public String getStepEntryMessage() {
        try {
            // handle lines with step indication but no entry message
            RegexMatcher regexmatcher = new RegexMatcher("^\\[Step \\d*\\/\\d*\\]$");
            if (regexmatcher.matches(this.message)) {
                return "";
            }

            // handle lines with step indication and entry message
            regexmatcher = new RegexMatcher("^\\[Step \\d*\\/\\d*\\] (.*)$");
            String[] components = regexmatcher.extractComponents(this.message);

            return components[0];
        } catch (Exception e) {
            return this.message;
        }
    }

    public String getOutput() {
        return toTabs(this.level - 1) +
                this.message;
    }

    public String getStepOutput() {
        return toTabs(this.level - 1) +
                this.getStepEntryMessage();
    }

    private String toTabs(int number) {
        return new String(new char[number]).replace("\0", "\t");
    }
}
