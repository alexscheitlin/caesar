package ch.scheitlin.alex.maven;

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

        // read goal log form test resources
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

        // read goal log form test resources
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

    private void assertErrors(List<Error> expectedErrors, List<Error> actualErrors) {
        Assert.assertEquals(expectedErrors.size(), actualErrors.size());

        for (int i = 0; i < expectedErrors.size(); i++) {
            Assert.assertEquals(expectedErrors.get(i).getPath(), actualErrors.get(i).getPath());
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