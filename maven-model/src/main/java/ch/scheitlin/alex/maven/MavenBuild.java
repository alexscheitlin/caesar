package ch.scheitlin.alex.maven;

import java.util.List;

public class MavenBuild {
    private MavenBuildStatus status;
    private MavenGoal failedGoal;
    private List<MavenModule> modules;

    public MavenBuild(MavenBuildStatus status, MavenGoal failedGoal) {
        this.status = status;
        this.failedGoal = failedGoal;
    }

    public MavenBuildStatus getStatus() {
        return this.status;
    }

    public MavenGoal getFailedGoal() {
        return this.failedGoal;
    }

    public List<MavenModule> getModules() {
        return this.modules;
    }

    public void setModules(List<MavenModule> modules) {
        this.modules = modules;
    }
}
