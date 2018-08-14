package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class TeamCityBuildStepRunnerTypeTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all runner types
    private void assertToString(TeamCityBuildStepRunnerType runnerType, String expectedString) {
        // execute method to be tested
        String actualString = runnerType.toString();

        // assert result
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_DOT_NET_CLI() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOT_NET_CLI;
        String expectedString = ".NET CLI (dotnet)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOT_NET_PROCESS_RUNNER() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOT_NET_PROCESS_RUNNER;
        String expectedString = ".NET Process Runner";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_ANT() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.ANT;
        String expectedString = "Ant";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_COMMAND_LINE() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.COMMAND_LINE;
        String expectedString = "Command Line";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_CONTAINER_DEPLOYER() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.CONTAINER_DEPLOYER;
        String expectedString = "Container Deployer";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_BUILD() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOCKER_BUILD;
        String expectedString = "Docker Build";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_COMPOSE() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DOCKER_COMPOSE;
        String expectedString = "Docker Compose";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_JAVA() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_JAVA;
        String expectedString = "Duplicates finder (Java)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_RESHARPER() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;
        String expectedString = "Duplicates finder (ReSharper)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FTP_UPLOAD() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.FTP_UPLOAD;
        String expectedString = "FTP Upload";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FXCOP() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.FXCOP;
        String expectedString = "FxCop";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_GRADLE() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.GRADLE;
        String expectedString = "Gradle";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_INTELLIJ_IDEA() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;
        String expectedString = "Inspections (IntelliJ IDEA)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_RESHARPER() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INSPECTIONS_RESHARPER;
        String expectedString = "Inspections (ReSharper)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INTELLIJ_IDEA_PROJECT() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.INTELLIJ_IDEA_PROJECT;
        String expectedString = "IntelliJ IDEA Project";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MAVEN() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MAVEN;
        String expectedString = "Maven";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSBUILD() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MSBUILD;
        String expectedString = "MSBuild";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSPEC() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.MSPEC;
        String expectedString = "MSpec";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NANT() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NANT;
        String expectedString = "NAnt";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_INSTALLER() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_INSTALLER;
        String expectedString = "NuGet Installer";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PACK() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_PACK;
        String expectedString = "NuGet Pack";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PUBLISH() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUGET_PUBLISH;
        String expectedString = "NuGet Publish";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUNIT() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.NUNIT;
        String expectedString = "NUnit";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_POWERSHELL() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.POWERSHELL;
        String expectedString = "PowerShell";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_RAKE() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.RAKE;
        String expectedString = "Rake";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SIMPLE_BUILD_TOOL_SCALA() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;
        String expectedString = "Simple Build Tool (Scala)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SMB_UPLOAD() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SMB_UPLOAD;
        String expectedString = "SMB Upload";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_EXEC() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SSH_EXEC;
        String expectedString = "SSH Exec";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_UPLOAD() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.SSH_UPLOAD;
        String expectedString = "SSH Upload";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_SLN() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_SLN;
        String expectedString = "Visual Studio (sln)";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_2003() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_2003;
        String expectedString = "Visual Studio 2003";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_TESTS() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_TESTS;
        String expectedString = "Visual Studio Tests";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_XCODE_PROJECT() {
        // assign variables with test data
        TeamCityBuildStepRunnerType runnerType = TeamCityBuildStepRunnerType.XCODE_PROJECT;
        String expectedString = "Xcode Project";

        // execute method to be tested and assert result
        assertToString(runnerType, expectedString);
    }

    // test toString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all runner types
    private void assertFromString(String string, TeamCityBuildStepRunnerType expectedRunnerType) {
        // execute method to be tested
        TeamCityBuildStepRunnerType actualRunnerType = TeamCityBuildStepRunnerType.fromString(string);

        // assert result
        Assert.assertEquals(expectedRunnerType, actualRunnerType);
    }

    @Test
    public void fromString_DOT_NET_CLI() {
        // assign variables with test data
        String string = ".NET CLI (dotnet)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOT_NET_CLI;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOT_NET_PROCESS_RUNNER() {
        // assign variables with test data
        String string = ".NET Process Runner";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOT_NET_PROCESS_RUNNER;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_ANT() {
        // assign variables with test data
        String string = "Ant";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.ANT;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_COMMAND_LINE() {
        // assign variables with test data
        String string = "Command Line";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.COMMAND_LINE;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_CONTAINER_DEPLOYER() {
        // assign variables with test data
        String string = "Container Deployer";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.CONTAINER_DEPLOYER;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_BUILD() {
        // assign variables with test data
        String string = "Docker Build";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOCKER_BUILD;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_COMPOSE() {
        // assign variables with test data
        String string = "Docker Compose";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DOCKER_COMPOSE;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_JAVA() {
        // assign variables with test data
        String string = "Duplicates finder (Java)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_JAVA;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_RESHARPER() {
        // assign variables with test data
        String string = "Duplicates finder (ReSharper)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FTP_UPLOAD() {
        // assign variables with test data
        String string = "FTP Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.FTP_UPLOAD;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FXCOP() {
        // assign variables with test data
        String string = "FxCop";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.FXCOP;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_GRADLE() {
        // assign variables with test data
        String string = "Gradle";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.GRADLE;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_INTELLIJ_IDEA() {
        // assign variables with test data
        String string = "Inspections (IntelliJ IDEA)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_RESHARPER() {
        // assign variables with test data
        String string = "Inspections (ReSharper)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INSPECTIONS_RESHARPER;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INTELLIJ_IDEA_PROJECT() {
        // assign variables with test data
        String string = "IntelliJ IDEA Project";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.INTELLIJ_IDEA_PROJECT;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MAVEN() {
        // assign variables with test data
        String string = "Maven";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MAVEN;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSBUILD() {
        // assign variables with test data
        String string = "MSBuild";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MSBUILD;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSPEC() {
        // assign variables with test data
        String string = "MSpec";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.MSPEC;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NANT() {
        // assign variables with test data
        String string = "NAnt";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NANT;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_INSTALLER() {
        // assign variables with test data
        String string = "NuGet Installer";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_INSTALLER;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PACK() {
        // assign variables with test data
        String string = "NuGet Pack";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_PACK;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PUBLISH() {
        // assign variables with test data
        String string = "NuGet Publish";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUGET_PUBLISH;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUNIT() {
        // assign variables with test data
        String string = "NUnit";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.NUNIT;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_POWERSHELL() {
        // assign variables with test data
        String string = "PowerShell";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.POWERSHELL;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_RAKE() {
        // assign variables with test data
        String string = "Rake";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.RAKE;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SIMPLE_BUILD_TOOL_SCALA() {
        // assign variables with test data
        String string = "Simple Build Tool (Scala)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SMB_UPLOAD() {
        // assign variables with test data
        String string = "SMB Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SMB_UPLOAD;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_EXEC() {
        // assign variables with test data
        String string = "SSH Exec";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SSH_EXEC;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_UPLOAD() {
        // assign variables with test data
        String string = "SSH Upload";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.SSH_UPLOAD;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_SLN() {
        // assign variables with test data
        String string = "Visual Studio (sln)";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_SLN;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_2003() {
        // assign variables with test data
        String string = "Visual Studio 2003";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_2003;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_TESTS() {
        // assign variables with test data
        String string = "Visual Studio Tests";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.VISUAL_STUDIO_TESTS;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_XCODE_PROJECT() {
        // assign variables with test data
        String string = "Xcode Project";
        TeamCityBuildStepRunnerType expectedRunnerType = TeamCityBuildStepRunnerType.XCODE_PROJECT;

        // execute method to be tested and assert result
        assertFromString(string, expectedRunnerType);
    }
    // test fromString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
