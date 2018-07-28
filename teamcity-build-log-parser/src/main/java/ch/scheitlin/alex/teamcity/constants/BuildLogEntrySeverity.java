package ch.scheitlin.alex.teamcity.constants;

public enum BuildLogEntrySeverity {
    UNKNOWN(" "),
    INFORMATION("i"),
    WARNING("W"),
    ERROR("E"),
    FAILURE("F");

    private String string;

    BuildLogEntrySeverity(String string) {
        this.string = string;
    }

    public String toString() {
        return this.string;
    }

    public static BuildLogEntrySeverity fromString(String string) {
        for (BuildLogEntrySeverity severity : BuildLogEntrySeverity.values()) {
            if (severity.string.equals(string)) {
                return severity;
            }
        }

        return null;
    }
}
