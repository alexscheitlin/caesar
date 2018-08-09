package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildServerTest {

    @Test
    public void getName() {
        // assign variables with test data
        BuildServerName expectedName = BuildServerName.TEAM_CITY;

        // allocate test objects
        BuildServer buildServer = new BuildServer(expectedName, null);

        // execute method to be tested
        BuildServerName actualName = buildServer.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getProjects() {
        // assign variables with test data
        List<Project> expectedProjects = new ArrayList<Project>();

        // allocate test objects
        BuildServer buildServer = new BuildServer(null, expectedProjects);

        // execute method to be tested
        List<Project> actualProjects = buildServer.getProjects();

        // assert result
        Assert.assertEquals(expectedProjects, actualProjects);
    }

    @Test public void getProject_projectExists() {
        // assign variables with test data
        Project expectedProject = new Project("Test", null);

        // allocate test objects
        List<Project> projects = new ArrayList<Project>();
        projects.add(expectedProject);

        BuildServer buildServer = new BuildServer(null, projects);

        // execute method to be tested
        Project actualProject = buildServer.getProject(expectedProject.getName());

        // assert result
        Assert.assertEquals(expectedProject, actualProject);
    }

    @Test public void getProject_projectDoesNotExists() {
        // allocate test objects
        Project project = new Project("Test", null);

        List<Project> projects = new ArrayList<Project>();
        projects.add(project);

        BuildServer buildServer = new BuildServer(null, projects);

        // execute method to be tested
        Project actualProject = buildServer.getProject("Test1");

        // assert result
        Assert.assertNull(actualProject);
    }

    @Test
    public void getProjectNames() {
        // assign variables with test data
        String[] expectedProjectNames = { "Project1", "Project2" };

        // allocate test objects
        Project project1 = new Project(expectedProjectNames[0], null);
        Project project2 = new Project(expectedProjectNames[1], null);

        List<Project> projects = new ArrayList<Project>();
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