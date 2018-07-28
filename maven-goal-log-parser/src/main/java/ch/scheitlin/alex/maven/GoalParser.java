package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;

import java.util.List;

public abstract class GoalParser {
    private String PLUGIN_NAME;
    private String GOAL_NAME;

    protected GoalParser(String pluginName, String goalName) {
        this.PLUGIN_NAME = pluginName;
        this.GOAL_NAME = goalName;
    }

    /**
     * Checks whether the parser is able to parse the a log of the specified goal of the specified plugin or not.
     *
     * @param plugin the name of the plugin of the goal to parse the log
     * @param goal the name of the goal to parse the log
     * @return
     */
    public boolean isParserFor(String plugin, String goal) {
        return plugin.equals(PLUGIN_NAME) && goal.equals(GOAL_NAME);
    }

    public abstract List<Error> parseLog(String log);
}
