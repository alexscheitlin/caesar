package ch.scheitlin.alex.teamcity;

/**
 * Represents the severity of an entry of a build log of team city.
 */
public enum TeamCityBuildLogEntrySeverity {
    UNKNOWN(" "),
    INFORMATION("i"),
    WARNING("W"),
    ERROR("E"),
    FAILURE("F");

    /**
     * The {@code String} representation of a team city build step runner type.
     */
    private String string;

    /**
     * Creates a new instance of a team city build log entry severity.
     *
     * @param string the {@code String} representation of a team city build log entry severity
     */
    TeamCityBuildLogEntrySeverity(String string) {
        this.string = string;
    }

    /**
     * Returns the team city build log entry severity as a {@code String}.
     *
     * @return the {@code String} representation of a team city build log entry severity
     */
    public String toString() {
        return this.string;
    }

    /**
     * Converts a {@code String} to the corresponding team city build log entry severity.
     *
     * @param string the {@code String} to convert to the corresponding team city build log entry severity
     * @return the team city build log entry severity or {@code null} if no corresponding team city build log entry
     * severity exists
     */
    public static TeamCityBuildLogEntrySeverity fromString(String string) {
        for (TeamCityBuildLogEntrySeverity severity : TeamCityBuildLogEntrySeverity.values()) {
            if (severity.string.equals(string)) {
                return severity;
            }
        }

        return null;
    }
}
