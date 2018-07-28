package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

public class MavencompilerCompile extends GoalParser {
    private final String START = "^\\[ERROR\\] COMPILATION ERROR :.*$";
    private final String ERROR = "^\\[ERROR\\] (.*)(?:\\\\|\\/)(.*\\.java):\\[(\\d+),(\\d+)\\] (?:error: |)(.*)$";
    private final String ERROR_NUMBER = "^\\[INFO\\] (.*) (?:error|errors).*$";

    public MavencompilerCompile() {
        super("maven-compiler-plugin", "compile");
    }

    @Override
    public List<Error> parseLog(String log) {
        // prepare matchers for regular expressions
        RegexMatcher startMatcher = new RegexMatcher(START);
        RegexMatcher errorMatcher = new RegexMatcher(ERROR);
        RegexMatcher errorNumberMatcher = new RegexMatcher(ERROR_NUMBER);

        boolean hasFoundStart = false;
        List<Error> errors = new ArrayList<Error>();
        for (String line : log.split("\n")) {
            // search the start of the section to parse
            if (!hasFoundStart) {
                if (startMatcher.matches(line)) {
                    hasFoundStart = true;
                }
                continue;
            }

            // search all error lines
            if (errorMatcher.matches(line)) {
                String[] components = errorMatcher.extractComponentsSilently(line);

                // extract path beginning at src
                String path = components[0];
                String[] parts = path.split("\\\\");
                if (parts.length == 1) {
                    parts = path.split("/");
                }

                String rest = "";
                boolean found = false;
                for (int i = 0; i < parts.length; i++) {
                    if (!found && parts[i].equals("src")) {
                        rest += parts[i];
                        found = true;
                    } else if (found) {
                        if (rest != "") {
                            rest += "/";
                        }
                        rest += parts[i];
                    }
                }

                String path2 = rest;
                String file = components[1];
                int line2 = Integer.valueOf(components[2]);
                int column = Integer.valueOf(components[3]);
                String message = components[4];
                Error error = new Error(path2, file, line2, column, message);

                errors.add(error);
                continue;
            }

            // search the number of errors that should have been found
            if (errorNumberMatcher.matches(line)) {
                if (Integer.valueOf(errorNumberMatcher.extractComponentsSilently(line)[0]) != errors.size()) {
                    System.out.println("The number of found errors is not as expected!");
                    System.out.println("\tExpected: " + errorNumberMatcher.extractComponentsSilently(line)[0]);
                    System.out.println("\tFound: " + errors.size());
                    return null;
                }
                return errors;
            }
        }

        if (!hasFoundStart) {
            System.out.println("Start of goal log not found!");
            return null;
        }

        System.out.println("End of goal log not found!");
        return null;
    }
}
