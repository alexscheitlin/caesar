package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServerType;
import ch.scheitlin.alex.teamcity.TeamCityBuild;

public abstract class Parser {
    private BuildServerType buildServerType;

    public Parser(BuildServerType buildServerType) {
        this.buildServerType = buildServerType;
    }

    public BuildServerType getBuildServerType() {
        return this.getBuildServerType();
    }

    public boolean isParserFor(BuildServerType buildServerType) {
        return buildServerType == this.buildServerType;
    }

    public abstract TeamCityBuild parseBuildLog(String log) throws Exception;
}
