package ch.scheitlin.alex.teamcity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Scanner;

public class BuildLogParserTest {

    @Test
    public void parseBuildLog() {
        // read team city build log form test resources
        String log = readResourceFile("team-city-build-log-1.log");

        TeamCityBuild build = null;
        try {
            build = BuildLogParser.parseBuildLog(log);
        } catch (Exception ex) {
            fail("Could not parse build log.");
        }

        //Assert.assertEquals("2017.2.3", build.getTeamCityServerVersion());
        Assert.assertEquals("Demo Bank Account", build.getProjectName());
        Assert.assertEquals("Maven Clean Test", build.getBuildConfigurationName());
        Assert.assertEquals("11", build.getNumber());
        Assert.assertEquals("compile-error", build.getBranchName());
        Assert.assertEquals(TeamCityBuildStatus.FAILURE, build.getStatus());
        Assert.assertEquals("Compilation error: Compiler (new)", build.getStatusText());
        Assert.assertEquals("Sat Jun 23 16:31:53 CEST 2018", build.getStartDate().toString());
        Assert.assertEquals("Sat Jun 23 16:32:00 CEST 2018", build.getFinishDate().toString());
        Assert.assertEquals("githubalexscheitlin/demo-bank-account", build.getVcsRootName());
        Assert.assertEquals("6a47dbbc35ac6a29583c2dc3bcbeb1bb54e7e3be", build.getCommitHash());
    }

    private String readResourceFile(String fileName) {
        StringBuilder result = new StringBuilder();
        File file = new File(getClass().getResource("/" + fileName).getFile());
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (Exception ext) {
            return null;
        }

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }

        scanner.close();

        return result.toString();
    }
}