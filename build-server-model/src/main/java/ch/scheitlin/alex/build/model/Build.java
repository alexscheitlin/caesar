package ch.scheitlin.alex.build.model;

public class Build {
    private String id;
    private String number;
    private boolean status;
    private String statusText;
    private String repository;
    private String branch;
    private String commit;

    public Build(String number, boolean status, String statusText) {
        this.number = number;
        this.status = status;
        this.statusText = statusText;
    }

    public Build(String id, String number, boolean status, String statusText, String repository, String branch, String commit) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.statusText = statusText;
        this.repository = repository;
        this.branch = branch;
        this.commit = commit;
    }

    public String getId() {
        return this.id;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public String getRepository() {
        return this.repository;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getCommit() {
        return this.commit;
    }
}
