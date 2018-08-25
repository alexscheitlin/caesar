package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

public class MavensurefireTestTest {
    @Test
    public void testClassToFullPath() {
        // assign variables with test data
        String testClass = "com.example.Class";
        String expectedFullPath = "src/test/java/com/example/Class.java";

        // execute method to be tested
        String actualFullPath = MavensurefireTest.testClassToFullPath(testClass);

        // assert result
        Assert.assertEquals(expectedFullPath, actualFullPath);
    }

    @Test
    public void getPathFromTestClass() {
        // assign variables with test data
        String testClass = "com.example.Class";
        String expectedPath = "src/test/java/com/example";

        // execute method to be tested
        String actualPath = MavensurefireTest.getPathFromTestClass(testClass);

        // assert result
        Assert.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void getFileFromTestClass() {
        // assign variables with test data
        String testClass = "com.example.Class";
        String expectedFile = "Class.java";

        // execute method to be tested
        String actualFile = MavensurefireTest.getFileFromTestClass(testClass);

        // assert result
        Assert.assertEquals(expectedFile, actualFile);
    }
}
