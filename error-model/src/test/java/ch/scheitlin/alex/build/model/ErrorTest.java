package ch.scheitlin.alex.build.model;

import org.junit.Assert;
import org.junit.Test;

public class ErrorTest {

    @Test
    public void getPath() {
        String expectedPath = "D:/some/path";

        Error error = new Error(expectedPath, null, 0, 0, null);

        String actualPath = error.getPath();

        Assert.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void getFile() {
        String expectedFile = "file.txt";

        Error error = new Error(null, expectedFile, 0, 0, null);

        String actualFile = error.getFile();

        Assert.assertEquals(expectedFile, actualFile);
    }

    @Test
    public void getLine() {
        int expectedLine = 1;

        Error error = new Error(null, null, expectedLine, 0, null);

        int actualLine = error.getLine();

        Assert.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void getColumn() {
        int expectedColumn = 1;

        Error error = new Error(null, null, 0, expectedColumn, null);

        int actualColumn = error.getColumn();

        Assert.assertEquals(expectedColumn, actualColumn);
    }

    @Test
    public void getMessage() {
        String expectedMessage = "message";

        Error error = new Error(null, null, 0, 0, expectedMessage);

        String actualMessage = error.getMessage();

        Assert.assertEquals(expectedMessage, actualMessage);
    }
}