package ch.scheitlin.alex.build.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<BuildConfiguration> buildConfigurations;

    public Project(String name, List<BuildConfiguration> buildConfigurations) {
        this.name = name;
        this.buildConfigurations = buildConfigurations;
    }

    public String getName() {
        return this.name;
    }

    public List<BuildConfiguration> getBuildConfigurations() {
        return this.buildConfigurations;
    }

    public List<String> getBuildConfigurationNames() {
        List<String> buildConfigurationNames = new ArrayList<String>();

        for (BuildConfiguration buildConfiguration : this.buildConfigurations) {
            buildConfigurationNames.add(buildConfiguration.getName());
        }

        return buildConfigurationNames;
    }
}
