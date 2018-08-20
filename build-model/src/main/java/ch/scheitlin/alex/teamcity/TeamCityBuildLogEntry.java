package ch.scheitlin.alex.teamcity;

import ch.scheitlin.alex.utils.RegexMatcher;

/**
 * Represents a build log entry (a single line) of a team city build log.
 */
public class TeamCityBuildLogEntry {
    /**
     * The line number of the log entry within the build log.
     */
    private int lineNumber;

    /**
     * The timestamp when the log entry was added to the build log.
     */
    private String time;

    /**
     * The severity of the log entry.
     */
    private TeamCityBuildLogEntrySeverity severity;

    /**
     * The level of the log entry (represented by tabs within the original build log).
     */
    private int level;

    /**
     * The log message of the log entry.
     */
    private String message;

    /**
     * Creates a new instance of a team city build log entry.
     *
     * @param lineNumber the line number of the log entry within the build log
     * @param time       the timestamp when the log entry was added to the build log
     * @param severity   the severity of the log entry
     * @param level      the level of the log entry (represented by tabs within the original build log)
     * @param message    the log message of the log entry
     */
    public TeamCityBuildLogEntry(int lineNumber, String time, TeamCityBuildLogEntrySeverity severity, int level, String message) {
        this.lineNumber = lineNumber;
        this.time = time;
        this.severity = severity;
        this.level = level;
        this.message = message;
    }

    /**
     * Gets the line number of the log entry within the build log.
     *
     * @return the line number of the log entry within the build log
     */
    public int getLineNumber() {
        return this.lineNumber;
    }

    /**
     * Gets the time stamp when the log entry was added to the build log.
     *
     * @return the time stamp when the log entry was added to the build log
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Gets the severity of the log entry.
     *
     * @return the severity of the log entry
     */
    public TeamCityBuildLogEntrySeverity getSeverity() {
        return this.severity;
    }

    /**
     * Gets the level of the log entry (represented by tabs within the original build log).
     *
     * @return the level of the log entry
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the log message of the log entry.
     *
     * @return the log message of the log entry
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Appends a new line to the build log entry message.
     *
     * @param line the message to add to the build log entry message
     */
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

    /**
     * Returns the original build log entry line.
     *
     * @return the original build log entry line
     */
    public String toOriginalString() {
        return "[" + this.time + "]" +
                this.severity.toString() + ": " +
                toTabs(this.level) +
                this.message;
    }

    /**
     * Gets the message of the build log entry without the leading step indicator (e.g. 'Step[1/2]').
     *
     * @return the message of the build log entry without the leading step indicator (e.g. 'Step[1/2]')
     */
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

    /**
     * Gets the message of the build log entry with leading tabs according to the level of the build log entry.
     *
     * @return the message of the build log entry with leading tabs according to the level of the build log entry
     */
    public String getOutput() {
        return toTabs(this.level - 1) +
                this.message;
    }

    /**
     * Gets the message of the build log entry with leading tabs according to the level of the build log entry and no
     * leading step indicator (e.g. 'Step[1/2]').
     *
     * @return the message of the build log entry with leading tabs according to the level of the build log entry and no
     * leading step indicator (e.g. 'Step[1/2]').
     */
    public String getStepOutput() {
        return toTabs(this.level - 1) +
                this.getStepEntryMessage();
    }

    /**
     * Creates a {@code String} containing a certain number of tabs.
     *
     * @param number the number of tabs to create
     * @return the {@code String} containing the specified number of tabs
     */
    private String toTabs(int number) {
        return new String(new char[number]).replace("\0", "\t");
    }
}
