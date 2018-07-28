package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;

import java.util.ArrayList;
import java.util.List;

public class MavenGoalLogParser {
    private List<GoalParser> goalParserList;

    /**
     * Creates a new instance of the MavenGoalLogParser class and instantiates all available goal parsers.
     */
    private MavenGoalLogParser() {
        this.goalParserList = new ArrayList<GoalParser>();

        // add a new instance of every goalParser to this list
        this.goalParserList.add(new MavencompilerCompile());
    }

    /**
     * Tries to parse the log of a goal with one of the available goal parsers.
     *
     * @param pluginName the plugin name of the log to parse
     * @param goalName the goal name of the log to parse
     * @param log the goal log to parse
     * @return the output of the parsed goal or {@code null} if no parser was found to parse the log of the specified
     * goal
     */
    public static List<Error> parseGoalLog(String pluginName, String goalName, String log) {
        return new MavenGoalLogParser().parse(pluginName, goalName, log);
    }

    /**
     * Tries to parse the log of a goal with one of the available goal parsers.
     *
     * @param pluginName the plugin name of the log to parse
     * @param goalName the goal name of the log to parse
     * @param log the goal log to parse
     * @return the output of the parsed goal or {@code null} if no parser was found to parse the log of the specified
     * goal
     */
    private List<Error> parse(String pluginName, String goalName, String log) {
        for (GoalParser parser : this.goalParserList) {
            if (parser.isParserFor(pluginName, goalName)) {
                return parser.parseLog(log);
            }
        }

        return null;
    }
}
