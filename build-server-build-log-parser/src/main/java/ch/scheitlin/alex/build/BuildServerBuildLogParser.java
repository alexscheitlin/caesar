package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.Build;
import ch.scheitlin.alex.build.model.BuildServerType;
import ch.scheitlin.alex.teamcity.TeamCityBuild;
import ch.scheitlin.alex.teamcity.TeamCityBuildLogParser;

import java.util.ArrayList;
import java.util.List;

public class BuildServerBuildLogParser {
    private Parser parser;
    private List<Parser> parserList;

    public BuildServerBuildLogParser(BuildServerType type) {
        this.parserList = new ArrayList<Parser>();

        // add a new instance of every build log parser
        this.parserList.add(new TeamCityBuildLogParser());

        // get the corresponding api
        for (Parser parser : parserList) {
            if (parser.isParserFor(type)) {
                this.parser = parser;
                break;
            }
        }
    }

    public Build parseBuildLog(String log) throws Exception {
        return this.parser.parseBuildLog(log);
    }
}
