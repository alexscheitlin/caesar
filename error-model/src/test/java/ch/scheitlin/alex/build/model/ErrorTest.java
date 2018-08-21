package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

public class ErrorTest {

    @Test
    public void getPath() {
        // assign variables with test data
        String expectedPath = "D:/some/path";

        // allocate test objects
        Error error = new Error(expectedPath, null, 0, 0, null);

        // execute method to be tested
        String actualPath = error.getPath();

        // assert result
        Assert.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void getFile() {
        // assign variables with test data
        String expectedFile = "file.txt";

        // allocate test objects
        Error error = new Error(null, expectedFile, 0, 0, null);

        // execute method to be tested
        String actualFile = error.getFile();

        // assert result
        Assert.assertEquals(expectedFile, actualFile);
    }

    @Test
    public void setAndGetLine() {
        // assign variables with test data
        int expectedLine = 1;

        // allocate test objects
        Error error = new Error(null, null, 0, 0, null);
        error.setLine(expectedLine);

        // execute method to be tested
        int actualLine = error.getLine();

        // assert result
        Assert.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void setAndGetColumn() {
        // assign variables with test data
        int expectedColumn = 1;

        // allocate test objects
        Error error = new Error(null, null, 0, 0, null);
        error.setColumn(expectedColumn);

        // execute method to be tested
        int actualColumn = error.getColumn();

        // assert result
        Assert.assertEquals(expectedColumn, actualColumn);
    }

    @Test
    public void getMessage() {
        // assign variables with test data
        String expectedMessage = "message";

        // allocate test objects
        Error error = new Error(null, null, 0, 0, expectedMessage);

        // execute method to be tested
        String actualMessage = error.getMessage();

        // assert result
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
