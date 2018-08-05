package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerTest {

    @Test
    public void getName() {
        BuildServerName expectedName = BuildServerName.TEAM_CITY;
        List<Project> projects = null;

        BuildServer buildServer = new BuildServer(expectedName, projects);

        BuildServerName actualName = buildServer.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getProjects() {
        Project project = new Project(null, null);

        List<Project> expectedProjects = new ArrayList<Project>();
        expectedProjects.add(project);

        BuildServer buildServer = new BuildServer(null, expectedProjects);

        List<Project> actualProjects = buildServer.getProjects();

        Assert.assertEquals(expectedProjects, actualProjects);
    }
}