package ch.scheitlin.alex.build.model;

/**
 * Represents an error occurred in a particular file.
 */
public class Error {
    /**
     * The path (directory) of the file where the error occurred.
     */
    private String path;

    /**
     * The name (and file ending) of the file where the error occurred.
     */
    private String file;

    /**
     * The line within the file where the error occurred.
     */
    private int line;

    /**
     * The column within the file where the error occurred.
     */
    private int column;

    /**
     * The error message describing the error.
     */
    private String message;

    /**
     * Creates a new instance of an error.
     *
     * @param path    the path (directory) of the file where the error occurred
     * @param file    the name (and file ending) of the file where the error occurred
     * @param line    the line within the file where the error occurred
     * @param column  the column within the file where the error occurred
     * @param message the error message describing the error
     */
    public Error(String path, String file, int line, int column, String message) {
        this.path = path;
        this.file = file;
        this.line = line;
        this.column = column;
        this.message = message;
    }

    /**
     * Gets the path (directory) of the file where the error occurred.
     *
     * @return the path (directory) of the file where the error occurred
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Sets the path (directory) of the file where the error occurred.
     *
     * @param path the path (directory) of the file where the error occurred
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the name (and file ending) of the file where the error occurred.
     *
     * @return the name (and file ending) of the file where the error occurred
     */
    public String getFile() {
        return this.file;
    }

    /**
     * Gets the line within the file where the error occurred.
     *
     * @return the line within the file where the error occurred
     */
    public int getLine() {
        return this.line;
    }

    /**
     * Sets the line within the file where the error occurred.
     *
     * @param line the line within the file where the error occurred
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Gets the column within the file where the error occurred.
     *
     * @return the column within the file where the error occurred
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets the column within the file where the error occurred.
     *
     * @param column the column within the file where the error occurred
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Gets the error message describing the error.
     *
     * @return the error message describing the error
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the error message describing the error.
     *
     * @param message the error message describing the error
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the full path (directory, file name, and file ending) of the file where the error occurred.
     *
     * @return the full path (directory, file name, and file ending) of the file where the error occurred (no leading or
     * trailing '/')
     */
    public String getFullPath() {
        String fullPath = "";

        // ignore path if it is null
        if (this.path != null) {
            fullPath += this.path;
        }

        // ignore file if it is null
        if (this.file != null) {
            if (fullPath != "") {
                fullPath += "/";
            }
            fullPath += this.file;
        }

        return fullPath;
    }
}
