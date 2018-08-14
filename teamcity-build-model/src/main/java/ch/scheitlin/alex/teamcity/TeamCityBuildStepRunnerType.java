package ch.scheitlin.alex.teamcity;

/**
 * <p>Represents a runner type of a team city build step.</p>
 *
 * <p>Reference: https://confluence.jetbrains.com/display/TCD18/Build+Runner</p>
 */
public enum TeamCityBuildStepRunnerType {
    DOT_NET_CLI(".NET CLI (dotnet)"),
    DOT_NET_PROCESS_RUNNER(".NET Process Runner"),
    ANT("Ant"),
    COMMAND_LINE("Command Line"),
    CONTAINER_DEPLOYER("Container Deployer"),
    DOCKER_BUILD("Docker Build"),
    DOCKER_COMPOSE("Docker Compose"),
    DUPLICATES_FINDER_JAVA("Duplicates finder (Java)"),
    DUPLICATES_FINDER_RESHARPER("Duplicates finder (ReSharper)"),
    FTP_UPLOAD("FTP Upload"),
    FXCOP("FxCop"),
    GRADLE("Gradle"),
    INSPECTIONS_INTELLIJ_IDEA("Inspections (IntelliJ IDEA)"),
    INSPECTIONS_RESHARPER("Inspections (ReSharper)"),
    INTELLIJ_IDEA_PROJECT("IntelliJ IDEA Project"),
    MAVEN("Maven"),
    MSBUILD("MSBuild"),
    MSPEC("MSpec"),
    NANT("NAnt"),
    NUGET_INSTALLER("NuGet Installer"),
    NUGET_PACK("NuGet Pack"),
    NUGET_PUBLISH("NuGet Publish"),
    NUNIT("NUnit"),
    POWERSHELL("PowerShell"),
    RAKE("Rake"),
    SIMPLE_BUILD_TOOL_SCALA("Simple Build Tool (Scala)"),
    SMB_UPLOAD("SMB Upload"),
    SSH_EXEC("SSH Exec"),
    SSH_UPLOAD("SSH Upload"),
    VISUAL_STUDIO_SLN("Visual Studio (sln)"),
    VISUAL_STUDIO_2003("Visual Studio 2003"),
    VISUAL_STUDIO_TESTS("Visual Studio Tests"),
    XCODE_PROJECT("Xcode Project");

    /**
     * The {@code String} representation of a team city build step runner type.
     */
    private String string;

    /**
     * Creates a new instance of a team city build step runner type.
     *
     * @param string the {@code String} representation of a team city build step runner type
     */
    TeamCityBuildStepRunnerType(String string) {
        this.string = string;
    }

    /**
     * Returns the team city build step runner type as a {@code String}.
     *
     * @return the {@code String} representation of a team city build step runner type
     */
    public String toString() {
        return this.string;
    }

    /**
     * Converts a {@code String} to the corresponding team city build step runner type.
     *
     * @param string the {@code String} to convert to the corresponding team city build step runner type
     * @return the team city build step runner type or {@code null} if no corresponding team city build step runner type
     * exists
     */
    public static TeamCityBuildStepRunnerType fromString(String string) {
        for (TeamCityBuildStepRunnerType type : TeamCityBuildStepRunnerType.values()) {
            if (type.string.equals(string)) {
                return type;
            }
        }

        return null;
    }
}
