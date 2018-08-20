package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerTest {

    @Test
    public void getType() {
        // assign variables with test data
        BuildServerType expectedType = BuildServerType.TEAM_CITY;

        // allocate test objects
        BuildServer buildServer = new BuildServer(expectedType, null);

        // execute method to be tested
        BuildServerType actualType = buildServer.getType();

        // assert result
        Assert.assertEquals(expectedType, actualType);
    }

    @Test
    public void getProjects() {
        // assign variables with test data
        List<BuildServerProject> expectedProjects = new ArrayList<BuildServerProject>();

        // allocate test objects
        BuildServer buildServer = new BuildServer(null, expectedProjects);

        // execute method to be tested
        List<BuildServerProject> actualProjects = buildServer.getProjects();

        // assert result
        Assert.assertEquals(expectedProjects, actualProjects);
    }

    @Test public void getProject_projectExists() {
        // assign variables with test data
        BuildServerProject expectedProject = new BuildServerProject("Test", null);

        // allocate test objects
        List<BuildServerProject> projects = new ArrayList<BuildServerProject>();
        projects.add(expectedProject);

        BuildServer buildServer = new BuildServer(null, projects);

        // execute method to be tested
        BuildServerProject actualProject = buildServer.getProject(expectedProject.getName());

        // assert result
        Assert.assertEquals(expectedProject, actualProject);
    }

    @Test public void getProject_projectDoesNotExists() {
        // allocate test objects
        BuildServerProject project = new BuildServerProject("Test", null);

        List<BuildServerProject> projects = new ArrayList<BuildServerProject>();
        projects.add(project);

        BuildServer buildServer = new BuildServer(null, projects);

        // execute method to be tested
        BuildServerProject actualProject = buildServer.getProject("Test1");

        // assert result
        Assert.assertNull(actualProject);
    }

    @Test
    public void getProjectNames() {
        // assign variables with test data
        String[] expectedProjectNames = { "Project1", "Project2" };

        // allocate test objects
        BuildServerProject project1 = new BuildServerProject(expectedProjectNames[0], null);
        BuildServerProject project2 = new BuildServerProject(expectedProjectNames[1], null);

        List<BuildServerProject> projects = new ArrayList<BuildServerProject>();
        projects.add(project1);
        projects.add(project2);

        BuildServer buildServer = new BuildServer(null, projects);

        // execute method to be tested
        List<String> actualProjectNames = buildServer.getProjectNames();

        // assert results
        Assert.assertEquals(expectedProjectNames.length, actualProjectNames.size());

        for (int i = 0; i < expectedProjectNames.length; i++) {
            Assert.assertEquals(expectedProjectNames[i], actualProjectNames.get(i));
        }
    }
}