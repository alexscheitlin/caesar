package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

public class MavensurefireTest extends GoalParser {
    private final String START_1 = "^-------------------------------------------------------$";
    private final String START_2 = "^ T E S T S$";
    private final String START_3 = "^-------------------------------------------------------$";
    private final String TEST_RUN = "^Tests run: \\d+, Failures: (\\d+), Errors: (\\d+), Skipped: \\d+, Time elapsed: .* sec.*$";
    private final String RESULTS = "^Results :$";
    private final String TEST_INDICATOR = "^(.*)\\((.*)\\)[:]?[ ]?(.*)?$";
    private final String FAILED_TESTS = "^Failed tests:(.*)$";
    private final String ERROR_TESTS = "^Tests in error:(.*)$";
    private final String TEST_RUN_SUMMARY = "^Tests run: \\d+, Failures: (\\d+), Errors: (\\d+), Skipped: \\d+$";

    public MavensurefireTest() {
        super("maven-surefire-plugin", "test");
    }

    @Override
    public List<Error> parseLog(String log) {
        // prepare matchers for regular expressions
        RegexMatcher startMatcher1 = new RegexMatcher(START_1);
        RegexMatcher startMatcher2 = new RegexMatcher(START_2);
        RegexMatcher startMatcher3 = new RegexMatcher(START_3);
        RegexMatcher testRunMatcher = new RegexMatcher(TEST_RUN);
        RegexMatcher resultsMatcher = new RegexMatcher(RESULTS);
        RegexMatcher testIndicatorMatcher = new RegexMatcher(TEST_INDICATOR);
        RegexMatcher failedTestsMatcher = new RegexMatcher(FAILED_TESTS);
        RegexMatcher errorTestsMatcher = new RegexMatcher(ERROR_TESTS);
        RegexMatcher testRunSummaryMatcher = new RegexMatcher(TEST_RUN_SUMMARY);

        int failedTestsCounter = 0;
        int errorTestsCounter = 0;

        int hasFoundStart = -2; // increase for every starting line found until 1
        boolean hasCountedBrokenTests = false;
        boolean hasCaughtFailedTests = false;
        boolean hasCaughtErrorTests = false;
        List<Error> errors = new ArrayList<Error>();
        for (String line : log.split("\n")) {
            // search the start of the section to parse
            if (hasFoundStart != 1) {
                if (hasFoundStart == -2 && startMatcher1.matches(line)) {
                    hasFoundStart = -1;
                } else if (hasFoundStart == -1 && startMatcher2.matches(line)) {
                    hasFoundStart = 0;
                } else if (hasFoundStart == 0 && startMatcher3.matches(line)) {
                    hasFoundStart = 1;
                }
                continue;
            }

            // count all tests indicated as "failures" and "errors"
            if (!hasCountedBrokenTests) {
                if (testRunMatcher.matches(line)) {
                    String[] components = testRunMatcher.extractComponentsSilently(line);
                    failedTestsCounter += Integer.valueOf(components[0]);
                    errorTestsCounter += Integer.valueOf(components[1]);
                } else if (resultsMatcher.matches(line)) {
                    // finish test counting and start test catching
                    hasCountedBrokenTests = true;
                }
                continue;
            }

            // catch failed tests
            if (!hasCaughtFailedTests) {
                if (failedTestsMatcher.matches(line)) {
                    // catch failed test mentioned on same line indicating the "Failed test" section
                    String possibleTest = failedTestsMatcher.extractComponentsSilently(line)[0];
                    if (testIndicatorMatcher.matches(possibleTest.trim())) {
                        String[] components = testIndicatorMatcher.extractComponentsSilently(possibleTest.trim());
                        errors.add(createError(components));
                        continue;
                    }
                } else if (testIndicatorMatcher.matches(line.trim())) {
                    // catch failed test mentioned on separate line
                    String[] components = testIndicatorMatcher.extractComponentsSilently(line.trim());
                    errors.add(createError(components));
                    continue;
                } else if (errorTestsMatcher.matches(line)) {
                    // finish catching failed tests and start catching error tests
                    hasCaughtFailedTests = true;
                } else {
                    continue;
                }
            }

            // catch error tests
            if (!hasCaughtErrorTests) {
                if (errorTestsMatcher.matches(line)) {
                    // catch error test mentioned on same line indicating the "Tests in error" section
                    String possibleTest = errorTestsMatcher.extractComponentsSilently(line)[0];
                    if (testIndicatorMatcher.matches(possibleTest.trim())) {
                        String[] components = testIndicatorMatcher.extractComponentsSilently(line.trim());
                        errors.add(createError(components));
                        continue;
                    }
                } else if (testIndicatorMatcher.matches(line.trim())) {
                    // catch error test mentioned on separate line
                    String[] components = testIndicatorMatcher.extractComponentsSilently(line.trim());
                    errors.add(createError(components));
                    continue;
                } else if (testRunSummaryMatcher.matches(line)) {
                    // finish catching error tests and catch test run summary
                    hasCaughtErrorTests = true;

                } else {
                    continue;
                }
            }

            // catch test run summary and check whether all broken tests were found
            if (hasCaughtErrorTests) {
                if (testRunSummaryMatcher.matches(line)) {
                    String[] components = testRunSummaryMatcher.extractComponentsSilently(line);
                    int totalTestFailures = Integer.valueOf(components[0]);
                    int totalTestErrors = Integer.valueOf(components[1]);

                    if (failedTestsCounter != totalTestFailures) {
                        System.out.println("The number of found tests that failed is not as expected!");
                        System.out.println("\tExpected: " + totalTestFailures);
                        System.out.println("\tFound: " + failedTestsCounter);
                        return null;
                    }

                    if (errorTestsCounter != totalTestErrors) {
                        System.out.println("The number of found tests in error is not as expected!");
                        System.out.println("\tExpected: " + totalTestErrors);
                        System.out.println("\tFound: " + errorTestsCounter);
                        return null;
                    }

                    break;
                }
            }
        }

        return errors;
    }

    private Error createError(String[] components) {
        String testMethod = components[0];
        String testClass = components[1];
        String testMessage = null;
        if (components.length == 3) {
            testMessage = components[2];
        }


        // TODO: testMethod needs to be passed too (additional information)
        String path = null;
        String file = "src/" + testClass.replaceAll("\\.", "/") + ".java";
        int line = 0;
        int column = 0;
        String message = testMessage;

        return new Error(path, file, line, column, message);
    }

}
