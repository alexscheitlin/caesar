package ch.scheitlin.alex.maven;

import java.util.ArrayList;
import java.util.List;

public class MavenModule {
    public String name;
    public String version;
    public MavenModuleStatus status;
    public String duration;
    public List<MavenGoal> goals;

    public MavenModule() {
        this.goals = new ArrayList<MavenGoal>();
    }
}
