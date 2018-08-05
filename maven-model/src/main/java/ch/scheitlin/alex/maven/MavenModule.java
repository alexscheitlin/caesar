package ch.scheitlin.alex.maven;

import java.util.ArrayList;
import java.util.List;

public class MavenModule {
    private String name;
    private String version;
    private MavenModuleStatus status;
    private String duration;
    private List<MavenGoal> goals;

    public MavenModule(String name) {
        this.name = name;
        this.goals = new ArrayList<MavenGoal>();
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public MavenModuleStatus getStatus() {
        return this.status;
    }

    public void setStatus(MavenModuleStatus status) {
        this.status = status;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<MavenGoal> getGoals() {
        return this.goals;
    }

    public void setGoals(List<MavenGoal> goals) {
        this.goals = goals;
    }

    public void addGoal(MavenGoal goal) {
        this.goals.add(goal);
    }
}
