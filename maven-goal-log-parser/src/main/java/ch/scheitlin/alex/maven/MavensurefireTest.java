package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.java.StackTrace;
import ch.scheitlin.alex.java.StackTraceParser;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Requires to parse the log of the executed goal of the maven build log.
 */
public class MavensurefireTest extends GoalParser {
    // regex for parsing the goal log
    private final String START_1 = "^-------------------------------------------------------$";
    private final String START_2 = "^ T E S T S$";
    private final String START_3 = "^-------------------------------------------------------$";
    private final String TEST_RUN = "^Tests run: \\d+, Failures: (\\d+), Errors: (\\d+), Skipped: \\d+, Time elapsed: .* sec.*$";
    private final String RESULTS = "^Results :$";
    private final String TEST_INDICATOR = "^(.*)\\((.*)\\)[:]?[ ]?(.*)?$";
    private final String FAILED_TESTS = "^Failed tests:(.*)$";
    private final String ERROR_TESTS = "^Tests in error:(.*)$";
    private final String TEST_RUN_SUMMARY = "^Tests run: \\d+, Failures: (\\d+), Errors: (\\d+), Skipped: \\d+$";

    // regex for parsing the build summary
    private final String SUMMARY_START_1 = "^\\[INFO\\] ------------------------------------------------------------------------$";
    private final String SUMMARY_START_2 = "^\\[INFO\\] BUILD FAILURE$";
    private final String SUMMARY_START_3 = "^\\[INFO\\] ------------------------------------------------------------------------$";
    private final String SUMMARY_CLASS_INDICATOR = "^((?:(?:[\\d\\w]*\\.)+[\\d\\w]*))$";
    private final String SUMMARY_TEST_INDICATOR = "^\\[((?:(?:[\\d\\w]*\\.)+[\\d\\w]*))\\] (.*)$";
    private final String SUMMARY_TEST_ERROR_INDICATOR = "^\\[(.*)\\] (.*)$";
    private final String SUMMARY_STACK_TRACE_ELEMENT = "^(\tat .*)$";

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
        RegexMatcher summaryStartMatcher1 = new RegexMatcher(SUMMARY_START_1);
        RegexMatcher summaryStartMatcher2 = new RegexMatcher(SUMMARY_START_2);
        RegexMatcher summaryStartMatcher3 = new RegexMatcher(SUMMARY_START_3);
        RegexMatcher summaryClassIndicatorMatcher = new RegexMatcher(SUMMARY_CLASS_INDICATOR);
        RegexMatcher summaryTestIndicatorMatcher = new RegexMatcher(SUMMARY_TEST_INDICATOR);
        RegexMatcher summaryTestErrorIndicatorMatcher = new RegexMatcher(SUMMARY_TEST_ERROR_INDICATOR);
        RegexMatcher summaryStackTraceElementMatcher = new RegexMatcher(SUMMARY_STACK_TRACE_ELEMENT);

        int failedTestsCounter = 0;
        int errorTestsCounter = 0;
        String currentClassInBuildSummary = null;
        String currentMethodInBuildSummary = null;
        String currentErrorInBuildSummary = null;
        List<String> currentStackTraceInBuildSummary = new ArrayList<String>();
        boolean lastLineWasStackTrace = false;
        int stackTraceCounter = 0;

        int hasFoundStart = -2; // increase for every starting line found until 1
        boolean hasCountedBrokenTests = false;
        boolean hasCaughtFailedTests = false;
        boolean hasCaughtErrorTests = false;
        int hasFoundSummaryStart = -2;  // increase for every starting line found until 1
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
                    }
                    continue;
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

                    continue;
                }
            }

            // search the start of the build summary section
            if (hasFoundSummaryStart != 1) {
                if (hasFoundSummaryStart == -2 && summaryStartMatcher1.matches(line)) {
                    hasFoundSummaryStart = -1;
                } else if (hasFoundSummaryStart == -1 && summaryStartMatcher2.matches(line)) {
                    hasFoundSummaryStart = 0;
                } else if (hasFoundSummaryStart == 0 && summaryStartMatcher3.matches(line)) {
                    hasFoundSummaryStart = 1;
                }
                continue;
            }

            // add stack trace to error
            // this if statement needs to be before all the following to finish a previously found stack trace
            if (summaryClassIndicatorMatcher.matches(line) || summaryTestIndicatorMatcher.matches(line.trim()) || summaryTestErrorIndicatorMatcher.matches(line.trim())) {
                if (lastLineWasStackTrace && currentStackTraceInBuildSummary.size() != 0) {
                    stackTraceCounter++;

                    for (Error error : errors) {
                        if (error.getFile().equals(testClassToPath(currentClassInBuildSummary)) &&
                                error.getMessage().equals(currentMethodInBuildSummary)) {
                            // parse stack trace
                            StackTrace stackTrace = null;
                            try {
                                stackTrace = StackTraceParser.parse(currentStackTraceInBuildSummary);
                            } catch (Exception e) {
                                System.out.println(e);
                                return null;
                            }

                            // get line in file
                            List<StackTraceElement> linesOfClass = stackTrace.getLinesOfPackage(currentClassInBuildSummary);
                            int lineInFile = linesOfClass.get(linesOfClass.size() - 1).getLineNumber();
                            error.setLine(lineInFile);

                            // set message
                            error.setMessage(currentErrorInBuildSummary);
                        }
                    }

                    // reset variables needed to catch stack trace
                    currentMethodInBuildSummary = null;
                    currentErrorInBuildSummary = null;
                    currentStackTraceInBuildSummary = new ArrayList<String>();
                    lastLineWasStackTrace = false;
                }
            }

            // catch test class
            if (summaryClassIndicatorMatcher.matches(line)) {
                currentClassInBuildSummary = summaryClassIndicatorMatcher.extractComponentsSilently(line)[0];
                continue;
            }

            // catch method
            if (currentClassInBuildSummary != null && summaryTestIndicatorMatcher.matches(line.trim())) {
                String[] components = summaryTestIndicatorMatcher.extractComponentsSilently(line.trim());

                if (components[0].equals(currentClassInBuildSummary)) {
                    currentMethodInBuildSummary = components[1];
                }
                continue;
            }

            // catch test error
            if (currentMethodInBuildSummary != null && summaryTestErrorIndicatorMatcher.matches(line.trim())) {
                String[] components = summaryTestErrorIndicatorMatcher.extractComponentsSilently(line.trim());

                if (components[0].equals(currentMethodInBuildSummary)) {
                    currentErrorInBuildSummary = components[1];
                    currentStackTraceInBuildSummary.add(currentErrorInBuildSummary);
                }
                continue;
            }

            // catch stack trace
            if (currentErrorInBuildSummary != null && summaryStackTraceElementMatcher.matches(line)) {
                currentStackTraceInBuildSummary.add(summaryStackTraceElementMatcher.extractComponentsSilently(line)[0]);
                lastLineWasStackTrace = true;
                continue;
            }
        }

        if (stackTraceCounter != errors.size()) {
            System.out.println("Not all stack traces found!");
            System.out.println("\tExpected: " + errors.size());
            System.out.println("\tFound: " + stackTraceCounter);
            return null;
        }

        return errors;
    }

    private Error createError(String[] components) {
        String testMethod = components[0];
        String testClass = components[1];

        String path = null;
        String file = testClassToPath(testClass);
        int line = 0;
        int column = 0;
        String message = testMethod;
        // set method as message to later (during stack trace parsing and adding line to this error) knowing the method
        // to find the right error and not only having the class

        return new Error(path, file, line, column, message);
    }

    private String testClassToPath(String testClass) {
        return "src/" + testClass.replaceAll("\\.", "/") + ".java";
    }

}
