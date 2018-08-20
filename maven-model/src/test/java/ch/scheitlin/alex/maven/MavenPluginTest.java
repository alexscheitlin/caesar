package ch.scheitlin.alex.maven;

import org.junit.Assert;
import org.junit.Test;

public class MavenPluginTest {

    @Test
    public void getName() {
        // assign variables with test data
        String expectedName = "Name";

        // allocate test objects
        MavenPlugin plugin = new MavenPlugin(expectedName, null, null);

        // execute method to be tested
        String actualName = plugin.getName();

        // assert result
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void getVendor() {
        // assign variables with test data
        String expectedVendor = "Vendor";

        // allocate test objects
        MavenPlugin plugin = new MavenPlugin(null, expectedVendor, null);

        // execute method to be tested
        String actualVendor = plugin.getVendor();

        // assert result
        Assert.assertEquals(expectedVendor, actualVendor);
    }

    @Test
    public void getVersion() {
        // assign variables with test data
        String expectedVersion = "Version";

        // allocate test objects
        MavenPlugin plugin = new MavenPlugin(null, null, expectedVersion);

        // execute method to be tested
        String actualVersion = plugin.getVersion();

        // assert result
        Assert.assertEquals(expectedVersion, actualVersion);
    }
}