package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

public class BuildStepRunnerTypeTest {

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test toString() method for all runner types
    private void assertToString(BuildStepRunnerType runnerType, String expectedString) {
        String actualString = runnerType.toString();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_DOT_NET_CLI() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DOT_NET_CLI;
        String expectedString = ".NET CLI (dotnet)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOT_NET_PROCESS_RUNNER() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DOT_NET_PROCESS_RUNNER;
        String expectedString = ".NET Process Runner";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_ANT() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.ANT;
        String expectedString = "Ant";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_COMMAND_LINE() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.COMMAND_LINE;
        String expectedString = "Command Line";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_CONTAINER_DEPLOYER() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.CONTAINER_DEPLOYER;
        String expectedString = "Container Deployer";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_BUILD() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DOCKER_BUILD;
        String expectedString = "Docker Build";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DOCKER_COMPOSE() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DOCKER_COMPOSE;
        String expectedString = "Docker Compose";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_JAVA() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DUPLICATES_FINDER_JAVA;
        String expectedString = "Duplicates finder (Java)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_DUPLICATES_FINDER_RESHARPER() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;
        String expectedString = "Duplicates finder (ReSharper)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FTP_UPLOAD() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.FTP_UPLOAD;
        String expectedString = "FTP Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_FXCOP() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.FXCOP;
        String expectedString = "FxCop";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_GRADLE() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.GRADLE;
        String expectedString = "Gradle";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_INTELLIJ_IDEA() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;
        String expectedString = "Inspections (IntelliJ IDEA)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INSPECTIONS_RESHARPER() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.INSPECTIONS_RESHARPER;
        String expectedString = "Inspections (ReSharper)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_INTELLIJ_IDEA_PROJECT() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.INTELLIJ_IDEA_PROJECT;
        String expectedString = "IntelliJ IDEA Project";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MAVEN() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.MAVEN;
        String expectedString = "Maven";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSBUILD() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.MSBUILD;
        String expectedString = "MSBuild";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_MSPEC() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.MSPEC;
        String expectedString = "MSpec";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NANT() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.NANT;
        String expectedString = "NAnt";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_INSTALLER() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.NUGET_INSTALLER;
        String expectedString = "NuGet Installer";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PACK() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.NUGET_PACK;
        String expectedString = "NuGet Pack";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUGET_PUBLISH() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.NUGET_PUBLISH;
        String expectedString = "NuGet Publish";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_NUNIT() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.NUNIT;
        String expectedString = "NUnit";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_POWERSHELL() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.POWERSHELL;
        String expectedString = "PowerShell";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_RAKE() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.RAKE;
        String expectedString = "Rake";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SIMPLE_BUILD_TOOL_SCALA() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;
        String expectedString = "Simple Build Tool (Scala)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SMB_UPLOAD() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.SMB_UPLOAD;
        String expectedString = "SMB Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_EXEC() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.SSH_EXEC;
        String expectedString = "SSH Exec";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_SSH_UPLOAD() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.SSH_UPLOAD;
        String expectedString = "SSH Upload";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_SLN() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.VISUAL_STUDIO_SLN;
        String expectedString = "Visual Studio (sln)";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_2003() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.VISUAL_STUDIO_2003;
        String expectedString = "Visual Studio 2003";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_VISUAL_STUDIO_TESTS() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.VISUAL_STUDIO_TESTS;
        String expectedString = "Visual Studio Tests";

        assertToString(runnerType, expectedString);
    }

    @Test
    public void toString_XCODE_PROJECT() {
        BuildStepRunnerType runnerType = BuildStepRunnerType.XCODE_PROJECT;
        String expectedString = "Xcode Project";

        assertToString(runnerType, expectedString);
    }

    // test toString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // test fromString() method for all runner types
    private void assertFromString(String string, BuildStepRunnerType expectedRunnerType) {
        BuildStepRunnerType actualRunnerType = BuildStepRunnerType.fromString(string);

        Assert.assertEquals(expectedRunnerType, actualRunnerType);
    }

    @Test
    public void fromString_DOT_NET_CLI() {
        String string = ".NET CLI (dotnet)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DOT_NET_CLI;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOT_NET_PROCESS_RUNNER() {
        String string = ".NET Process Runner";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DOT_NET_PROCESS_RUNNER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_ANT() {
        String string = "Ant";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.ANT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_COMMAND_LINE() {
        String string = "Command Line";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.COMMAND_LINE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_CONTAINER_DEPLOYER() {
        String string = "Container Deployer";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.CONTAINER_DEPLOYER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_BUILD() {
        String string = "Docker Build";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DOCKER_BUILD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DOCKER_COMPOSE() {
        String string = "Docker Compose";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DOCKER_COMPOSE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_JAVA() {
        String string = "Duplicates finder (Java)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DUPLICATES_FINDER_JAVA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_DUPLICATES_FINDER_RESHARPER() {
        String string = "Duplicates finder (ReSharper)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.DUPLICATES_FINDER_RESHARPER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FTP_UPLOAD() {
        String string = "FTP Upload";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.FTP_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_FXCOP() {
        String string = "FxCop";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.FXCOP;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_GRADLE() {
        String string = "Gradle";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.GRADLE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_INTELLIJ_IDEA() {
        String string = "Inspections (IntelliJ IDEA)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.INSPECTIONS_INTELLIJ_IDEA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INSPECTIONS_RESHARPER() {
        String string = "Inspections (ReSharper)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.INSPECTIONS_RESHARPER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_INTELLIJ_IDEA_PROJECT() {
        String string = "IntelliJ IDEA Project";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.INTELLIJ_IDEA_PROJECT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MAVEN() {
        String string = "Maven";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.MAVEN;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSBUILD() {
        String string = "MSBuild";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.MSBUILD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_MSPEC() {
        String string = "MSpec";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.MSPEC;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NANT() {
        String string = "NAnt";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.NANT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_INSTALLER() {
        String string = "NuGet Installer";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.NUGET_INSTALLER;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PACK() {
        String string = "NuGet Pack";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.NUGET_PACK;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUGET_PUBLISH() {
        String string = "NuGet Publish";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.NUGET_PUBLISH;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_NUNIT() {
        String string = "NUnit";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.NUNIT;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_POWERSHELL() {
        String string = "PowerShell";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.POWERSHELL;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_RAKE() {
        String string = "Rake";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.RAKE;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SIMPLE_BUILD_TOOL_SCALA() {
        String string = "Simple Build Tool (Scala)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.SIMPLE_BUILD_TOOL_SCALA;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SMB_UPLOAD() {
        String string = "SMB Upload";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.SMB_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_EXEC() {
        String string = "SSH Exec";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.SSH_EXEC;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_SSH_UPLOAD() {
        String string = "SSH Upload";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.SSH_UPLOAD;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_SLN() {
        String string = "Visual Studio (sln)";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.VISUAL_STUDIO_SLN;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_2003() {
        String string = "Visual Studio 2003";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.VISUAL_STUDIO_2003;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_VISUAL_STUDIO_TESTS() {
        String string = "Visual Studio Tests";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.VISUAL_STUDIO_TESTS;

        assertFromString(string, expectedRunnerType);
    }

    @Test
    public void fromString_XCODE_PROJECT() {
        String string = "Xcode Project";
        BuildStepRunnerType expectedRunnerType = BuildStepRunnerType.XCODE_PROJECT;

        assertFromString(string, expectedRunnerType);
    }
    // test fromString() method for all runner types
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
