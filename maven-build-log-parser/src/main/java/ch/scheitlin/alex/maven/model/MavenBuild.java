package ch.scheitlin.alex.maven.model;

import ch.scheitlin.alex.maven.constants.MavenBuildStatus;

import java.util.List;

public class MavenBuild {
    public MavenBuildStatus status;
    public MavenGoal failedGoal;
    public List<MavenModule> modules;
}
