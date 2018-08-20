package ch.scheitlin.alex.build.model;

/**
 * Represents a build of a build server.
 */
public class BuildServerBuild {
    /**
     * The id of the build on a build server.
     */
    private String id;

    /**
     * The number of the build of a build configuration on a build server.
     */
    private String number;

    /**
     * The status of the build (whether it was successful or not).
     */
    private boolean status;

    /**
     * The status text of the build.
     */
    private String statusText;

    /**
     * The name of the repository of the code of the build configured on a build server.
     */
    private String repository;

    /**
     * The name of the branch of the version control system of the code of the build.
     */
    private String branch;

    /**
     * The commit id of the commit in the version control system of the code of the build.
     */
    private String commit;

    /**
     * Creates a new instance of a build.
     *
     * @param number     the number of the build of a build configuration on a build server
     * @param status     the status of the build (whether it was successful or not)
     * @param statusText the status text of the build
     */
    public BuildServerBuild(String number, boolean status, String statusText) {
        this.number = number;
        this.status = status;
        this.statusText = statusText;
    }

    /**
     * Creates a new instance of a build.
     *
     * @param id         the id of the build on a build server
     * @param number     the number of the build of a build configuration on a build server
     * @param status     the status of the build (whether it was successful or not)
     * @param statusText the status text of the build
     * @param repository the name of the repository of the code of the build configured on a build server
     * @param branch     the name of the branch of the version control system of the code of the build
     * @param commit     the commit id of the commit in the version control system of the code of the build
     */
    public BuildServerBuild(String id, String number, boolean status, String statusText, String repository, String branch, String commit) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.statusText = statusText;
        this.repository = repository;
        this.branch = branch;
        this.commit = commit;
    }

    /**
     * Gets the id of the build on a build server
     *
     * @return the id of the build on a build server
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the number of the build of a build configuration on a build server
     *
     * @return the number of the build of a build configuration on a build server
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Gets the status of the build (whether it was successful or not).
     *
     * @return {@code true} if the build was successful and {@code false} if the build was not successful
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Gets the status text of the build
     *
     * @return the status text of the build
     */
    public String getStatusText() {
        return this.statusText;
    }

    /**
     * Gets the name of the repository of the code of the build configured on a build server.
     *
     * @return the name of the repository of the code of the build configured on a build server
     */
    public String getRepository() {
        return this.repository;
    }

    /**
     * Gets the name of the branch of the version control system of the code of the build.
     *
     * @return the name of the branch of the version control system of the code of the build.
     */
    public String getBranch() {
        return this.branch;
    }

    /**
     * Gets the commit id of the commit in the version control system of the code of the build.
     *
     * @return the commit id of the commit in the version control system of the code of the build
     */
    public String getCommit() {
        return this.commit;
    }
}
