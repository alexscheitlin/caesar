package ch.scheitlin.alex.maven;

import static org.junit.Assert.fail;

import ch.scheitlin.alex.build.model.Error;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MavenGoalLogParserTest {

    @Test
    public void parseGoalLog_MavencompilerCompile_oneError() {
        // define plugin and goal
        String pluginName = "maven-compiler-plugin";
        String goalName = "compile";

        // read goal log from test resources
        String log = readResourceFile(pluginName + "-" + goalName + "-1.txt");

        // define expected errors
        List<Error> expectedErrors = new ArrayList<Error>();
        String path = "src/main/java/com/example/bank";
        String file = "Main.java";
        int line = 5;
        int column = 27;
        String message = "';' expected";
        Error expectedError1 = new Error(path, file, line, column, message);
        expectedErrors.add(expectedError1);

        // parse goal log
        List<Error> actualErrors = MavenGoalLogParser.parseGoalLog(pluginName, goalName, log);

        // assert
        assertErrors(expectedErrors, actualErrors);
    }

    @Test
    public void parseGoalLog_MavencompilerCompile_twoErrors() {
        // define plugin and goal
        String pluginName = "maven-compiler-plugin";
        String goalName = "compile";

        // read goal log from test resources
        String log = readResourceFile(pluginName + "-" + goalName + "-2.txt");

        // define expected errors
        List<Error> expectedErrors = new ArrayList<Error>();
        String path1 = "src/main/java/com/example/bank";
        String file1 = "Main.java";
        int line1 = 5;
        int column1 = 28;
        String message1 = "';' expected";
        Error expectedError1 = new Error(path1, file1, line1, column1, message1);
        expectedErrors.add(expectedError1);
        String path2 = "src/main/java/com/example/bank";
        String file2 = "Main.java";
        int line2 = 16;
        int column2 = 11;
        String message2 = "'catch' without 'try'";
        Error expectedError2 = new Error(path2, file2, line2, column2, message2);
        expectedErrors.add(expectedError2);

        // parse goal log
        List<Error> actualErrors = MavenGoalLogParser.parseGoalLog(pluginName, goalName, log);

        // assert
        assertErrors(expectedErrors, actualErrors);
    }

    @Test
    public void parseGoalLog_CouldNotResolveDependencies() {
        String errorMessage = "Could not resolve dependencies";

        // read goal log from test resources
        String log = readResourceFile("could-not-resolve-dependencies.txt");

        // define expected errors
        List<Error> expectedErrors = new ArrayList<Error>();
        String path = null;
        String file = "pom.xml";
        int line = 0;
        int column = 0;
        String message = "Could not find artifact: junit:junit:5.12 (version '5.12' is locally not available)\n" +
                "Please check why this version can not be found on maven central or in any of the defined repositories in the pom.xml file. Does the <version> really exist?\n" +
                "Check all available versions of this artifact on maven central: https://mvnrepository.com/artifact/junit/junit";
        Error expectedError1 = new Error(path, file, line, column, message);
        expectedErrors.add(expectedError1);

        // parse goal log
        List<Error> actualErrors = MavenGoalLogParser.parseErrorLog(errorMessage, log);

        // assert
        assertErrors(expectedErrors, actualErrors);
    }

    @Test
    public void parseGoalLog_MavensurefireTest() {
        // define plugin and goal
        String pluginName = "maven-surefire-plugin";
        String goalName = "test";

        // read goal log from test resources
        String log = readResourceFile(pluginName + "-" + goalName + ".txt");

        // define expected errors
        List<Error> expectedErrors = new ArrayList<Error>();
        String path1 = "src/test/java/com/example/bank";
        String file1 = "BankAccountTest.java";
        int line1 = 21;
        int column1 = 0;
        String message1 = "java.lang.AssertionError";
        Error expectedError1 = new Error(path1, file1, line1, column1, message1);
        expectedErrors.add(expectedError1);
        String path2 = "src/test/java/com/example/bank";
        String file2 = "BankAccountTest.java";
        int line2 = 49;
        int column2 = 0;
        String message2 = "java.lang.AssertionError: expected:<1000000.0> but was:<1000001.0>";
        Error expectedError2 = new Error(path2, file2, line2, column2, message2);
        expectedErrors.add(expectedError2);
        String path3 = "src/test/java/com/example/bank";
        String file3 = "TableTest.java";
        int line3 = 26;
        int column3 = 0;
        String message3 = "java.lang.AssertionError";
        Error expectedError3 = new Error(path3, file3, line3, column3, message3);
        expectedErrors.add(expectedError3);
        String path4 = "src/test/java/com/example/bank";
        String file4 = "BankAccountTest.java";
        int line4 = 29;
        int column4 = 0;
        String message4 = "java.lang.IllegalArgumentException: Deposit amount less than zero";
        Error expectedError4 = new Error(path4, file4, line4, column4, message4);
        expectedErrors.add(expectedError4);

        // parse goal log
        List<Error> actualErrors = MavenGoalLogParser.parseGoalLog(pluginName, goalName, log);

        // assert
        assertErrors(expectedErrors, actualErrors);
    }

    @Test
    public void parseGoalLog_UnknownGoal() {
        String pluginName = "plugin";
        String goalName = "goal";
        String log = "log";
        List<Error> expectedErrors = null;

        // parse goal log
        List<Error> actualErrors = MavenGoalLogParser.parseGoalLog(pluginName, goalName, log);

        // assert
        Assert.assertEquals(expectedErrors, actualErrors);

    }

    private void assertErrors(List<Error> expectedErrors, List<Error> actualErrors) {
        Assert.assertEquals(expectedErrors.size(), actualErrors.size());

        for (int i = 0; i < expectedErrors.size(); i++) {
            if (expectedErrors.get(i).getPath() != null && actualErrors.get(i).getPath() != null) {
                Assert.assertEquals(expectedErrors.get(i).getPath(), actualErrors.get(i).getPath());
            } else if (expectedErrors.get(i).getPath() == null && actualErrors.get(i).getPath() != null ||
                    expectedErrors.get(i).getPath() != null && actualErrors.get(i).getPath() == null) {
                fail();
            }
            Assert.assertEquals(expectedErrors.get(i).getFile(), actualErrors.get(i).getFile());
            Assert.assertEquals(expectedErrors.get(i).getLine(), actualErrors.get(i).getLine());
            Assert.assertEquals(expectedErrors.get(i).getColumn(), actualErrors.get(i).getColumn());
            Assert.assertEquals(expectedErrors.get(i).getMessage(), actualErrors.get(i).getMessage());
        }
    }

    private String readResourceFile(String fileName) {
        StringBuilder result = new StringBuilder();
        File file = new File(getClass().getResource("/" + fileName).getFile());
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (Exception ext) {
            return null;
        }

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }

        scanner.close();

        return result.toString();
    }
}