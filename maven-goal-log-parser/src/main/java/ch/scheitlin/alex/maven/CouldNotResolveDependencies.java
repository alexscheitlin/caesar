package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Requires to parse the build summary section of the maven build log.
 */
public class CouldNotResolveDependencies extends GoalParser {
    private final String REGEX = "^\\[ERROR\\] Failed to execute goal on project .*: (Could not resolve dependencies) for project .*: (.*)$";

    public CouldNotResolveDependencies() {
        super( "Could not resolve dependencies");
    }

    @Override
    public List<Error> parseLog(String log) {
        // prepare matchers for regular expressions
        RegexMatcher regexMatcher = new RegexMatcher(REGEX);

        List<Error> errors = new ArrayList<Error>();
        for (String line : log.split("\n")) {
            // search all error lines
            if (regexMatcher.matches(line)) {
                String[] components = regexMatcher.extractComponentsSilently(line);

                String errorMessage = components[0];
                String errorDescription = components[1];

                Error error = new Error(null, "pom.xml", 0, 0, errorMessage + "\n" + errorDescription);

                errors.add(error);
                continue;
            }
        }
        return errors;
    }
}
