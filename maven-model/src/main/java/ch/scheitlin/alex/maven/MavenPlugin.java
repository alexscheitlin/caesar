package ch.scheitlin.alex.maven;

/**
 * Represents a maven plugin.
 */
public class MavenPlugin {
    /**
     * The name of the maven plugin.
     */
    private String name;

    /**
     * The vendor of the maven plugin.
     */
    private String vendor;

    /**
     * The version of the maven plugin.
     */
    private String version;

    /**
     * Creates a new instance of a maven plugin.
     *
     * @param name    the name of the maven plugin
     * @param vendor  the vendor of the maven plugin
     * @param version the version of the maven plugin
     */
    public MavenPlugin(String name, String vendor, String version) {
        this.name = name;
        this.vendor = vendor;
        this.version = version;
    }

    /**
     * Gets the name of the maven plugin.
     *
     * @return the name of the maven plugin
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the vendor of the maven plugin.
     *
     * @return the vendor of the maven plugin
     */
    public String getVendor() {
        return this.vendor;
    }

    /**
     * Gets the version of the maven plugin.
     *
     * @return the version of the maven plugin
     */
    public String getVersion() {
        return this.version;
    }
}
