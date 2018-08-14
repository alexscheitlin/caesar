package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;

import java.util.List;

public abstract class GoalParser {
    private String PLUGIN_NAME;
    private String GOAL_NAME;
    private String ERROR_MESSAGE;

    protected GoalParser(String pluginName, String goalName) {
        this.PLUGIN_NAME = pluginName;
        this.GOAL_NAME = goalName;
    }

    protected GoalParser(String errorMessage) {
        this.ERROR_MESSAGE = errorMessage;
    }

    /**
     * Checks whether the parser is able to parse the log of the specified goal of the specified plugin or not.
     *
     * @param plugin the name of the plugin of the goal to parse the log
     * @param goal   the name of the goal to parse the log
     * @return {@code true} if this parser is able to parse the specified goal of the specified plugin
     * or {@code false} if not
     */
    public boolean isParserForPlugin(String plugin, String goal) {
        if (PLUGIN_NAME == null || GOAL_NAME == null) {
            return false;
        }

        return plugin.equals(PLUGIN_NAME) && goal.equals(GOAL_NAME);
    }

    /**
     * Checks whether the parser is able to parse the log of the specified error message.
     *
     * @param message the error message
     * @return {@code true} if this parser is able to parse the specified error message
     * or {@code false} if not
     */
    public boolean isParserForErrorMessage(String message) {
        if (ERROR_MESSAGE == null) {
            return false;
        }

        return message.equals(ERROR_MESSAGE);
    }

    public abstract List<Error> parseLog(String log);
}
