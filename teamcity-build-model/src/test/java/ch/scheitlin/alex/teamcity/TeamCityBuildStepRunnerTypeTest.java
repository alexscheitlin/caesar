package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildStepRunnerTypeTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all runner types
    private void assertToString(TeamCityBuildStepRunnerType runnerType, String expectedString) {
        String actualString = runnerType.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_DOT_NET_CLI() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOT_NET_CLI;
        String expectedString = ".NET CLI (dotnet)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOT_NET_PROCESS_RUNNER() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOT_NET_PROCESS_RUNNER;
        String expectedString = ".NET Process Runner";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_ANT() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.ANT;
        String expectedString = "Ant";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_COMMAND_LINE() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.COMMAND_LINE;
        String expectedString = "Command Line";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_CONTAINER_DEPLOYER() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.CONTAINER_DEPLOYER;
        String expectedString = "Container Deployer";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_BUILD() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOCKER_BUILD;
        String expectedString = "Docker Build";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_COMPOSE() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOCKER_COMPOSE;
        String expectedString = "Docker Compose";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_JAVA() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_JAVA;
        String expectedString = "Duplicates finder (Java)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_RESHARPER() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;
        String expectedString = "Duplicates finder (ReSharper)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FTP_UPLOAD() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.FTP_UPLOAD;
        String expectedString = "FTP Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FXCOP() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.FXCOP;
        String expectedString = "FxCop";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_GRADLE() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.GRADLE;
        String expectedString = "Gradle";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_INTELLIJ_IDEA() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;
        String expectedString = "Inspections (IntelliJ IDEA)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_RESHARPER() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INSPECTIONS_RESHARPER;
        String expectedString = "Inspections (ReSharper)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INTELLIJ_IDEA_PROJECT() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INTELLIJ_IDEA_PROJECT;
        String expectedString = "IntelliJ IDEA Project";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MAVEN() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MAVEN;
        String expectedString = "Maven";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSBUILD() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MSBUILD;
        String expectedString = "MSBuild";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSPEC() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MSPEC;
        String expectedString = "MSpec";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NANT() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NANT;
        String expectedString = "NAnt";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_INSTALLER() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_INSTALLER;
        String expectedString = "NuGet Installer";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PACK() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_PACK;
        String expectedString = "NuGet Pack";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PUBLISH() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_PUBLISH;
        String expectedString = "NuGet Publish";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUNIT() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUNIT;
        String expectedString = "NUnit";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_POWERSHELL() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.POWERSHELL;
        String expectedString = "PowerShell";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_RAKE() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.RAKE;
        String expectedString = "Rake";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SIMPLE_BUILD_TOOL_SCALA() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;
        String expectedString = "Simple Build Tool (Scala)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SMB_UPLOAD() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SMB_UPLOAD;
        String expectedString = "SMB Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_EXEC() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SSH_EXEC;
        String expectedString = "SSH Exec";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_UPLOAD() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SSH_UPLOAD;
        String expectedString = "SSH Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_SLN() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_SLN;
        String expectedString = "Visual Studio (sln)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_2003() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_2003;
        String expectedString = "Visual Studio 2003";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_TESTS() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_TESTS;
        String expectedString = "Visual Studio Tests";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_XCODE_PROJECT() {
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.XCODE_PROJECT;
        String expectedString = "Xcode Project";

        assertToString(runnerType, expectedString);
    }

    // test toString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all runner types
    private void assertFromString(String string, TeamCityBuildStepRunnerType expectedRunnerType) {
        TeamCityBuildStepRunnerType actualRunnerType = TeamCityBuildStepRunnerType.fromString(string);

        Assert.assertEquals(expectedRunnerType, actualRunnerType);
    }

    @Test
    public void fromString_DOT_NET_CLI() {
        String string = ".NET CLI (dotnet)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOT_NET_CLI;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOT_NET_PROCESS_RUNNER() {
        String string = ".NET Process Runner";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOT_NET_PROCESS_RUNNER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_ANT() {
        String string = "Ant";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.ANT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_COMMAND_LINE() {
        String string = "Command Line";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.COMMAND_LINE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_CONTAINER_DEPLOYER() {
        String string = "Container Deployer";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.CONTAINER_DEPLOYER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_BUILD() {
        String string = "Docker Build";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOCKER_BUILD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_COMPOSE() {
        String string = "Docker Compose";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOCKER_COMPOSE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_JAVA() {
        String string = "Duplicates finder (Java)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_JAVA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_RESHARPER() {
        String string = "Duplicates finder (ReSharper)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FTP_UPLOAD() {
        String string = "FTP Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.FTP_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FXCOP() {
        String string = "FxCop";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.FXCOP;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_GRADLE() {
        String string = "Gradle";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.GRADLE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_INTELLIJ_IDEA() {
        String string = "Inspections (IntelliJ IDEA)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_RESHARPER() {
        String string = "Inspections (ReSharper)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INSPECTIONS_RESHARPER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INTELLIJ_IDEA_PROJECT() {
        String string = "IntelliJ IDEA Project";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INTELLIJ_IDEA_PROJECT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MAVEN() {
        String string = "Maven";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MAVEN;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSBUILD() {
        String string = "MSBuild";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MSBUILD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSPEC() {
        String string = "MSpec";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MSPEC;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NANT() {
        String string = "NAnt";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NANT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_INSTALLER() {
        String string = "NuGet Installer";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_INSTALLER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PACK() {
        String string = "NuGet Pack";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_PACK;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PUBLISH() {
        String string = "NuGet Publish";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_PUBLISH;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUNIT() {
        String string = "NUnit";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUNIT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_POWERSHELL() {
        String string = "PowerShell";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.POWERSHELL;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_RAKE() {
        String string = "Rake";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.RAKE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SIMPLE_BUILD_TOOL_SCALA() {
        String string = "Simple Build Tool (Scala)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SMB_UPLOAD() {
        String string = "SMB Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SMB_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_EXEC() {
        String string = "SSH Exec";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SSH_EXEC;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_UPLOAD() {
        String string = "SSH Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SSH_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_SLN() {
        String string = "Visual Studio (sln)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_SLN;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_2003() {
        String string = "Visual Studio 2003";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_2003;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_TESTS() {
        String string = "Visual Studio Tests";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_TESTS;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_XCODE_PROJECT() {
        String string = "Xcode Project";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.XCODE_PROJECT;

        assertFromString(string, expectedRunnerType);
    }
    // test fromString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
