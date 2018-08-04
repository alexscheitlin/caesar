package ch.scheitlin.alex.maven;

import java.util.List;

public class MavenBuild {
    public MavenBuildStatus status;
    public MavenGoal failedGoal;
    public List<MavenModule> modules;
}
