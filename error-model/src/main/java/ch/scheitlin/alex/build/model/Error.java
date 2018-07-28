package ch.scheitlin.alex.build.model;

public class Error {
    private String path;
    private String file;
    private int line;
    private int column;
    private String message;

    public Error(String path, String file, int line, int column, String message) {
        this.path = path;
        this.file = file;
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public String getPath() {
        return this.path;
    }

    public String getFile() {
        return this.file;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public String getMessage() {
        return this.message;
    }
}
