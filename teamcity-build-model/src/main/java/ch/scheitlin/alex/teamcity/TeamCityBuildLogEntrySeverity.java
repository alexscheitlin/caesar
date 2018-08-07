package ch.scheitlin.alex.teamcity;

public enum TeamCityBuildLogEntrySeverity {
    UNKNOWN(" "),
    INFORMATION("i"),
    WARNING("W"),
    ERROR("E"),
    FAILURE("F");

    private String string;

    TeamCityBuildLogEntrySeverity(String string) {
        this.string = string;
    }

    public String toString() {
        return this.string;
    }

    public static TeamCityBuildLogEntrySeverity fromString(String string) {
        for (TeamCityBuildLogEntrySeverity severity : TeamCityBuildLogEntrySeverity.values()) {
            if (severity.string.equals(string)) {
                return severity;
            }
        }

        return null;
    }
}
