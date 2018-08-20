package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServer;
import ch.scheitlin.alex.build.model.BuildServerType;

import java.io.File;
import java.util.Scanner;

public abstract class Api {
    private BuildServerType buildServerType;

    public Api(BuildServerType buildServerType) {
        this.buildServerType = buildServerType;
    }

    public BuildServerType getBuildServerType() {
        return this.getBuildServerType();
    }

    public boolean isApiFor(BuildServerType buildServerType) {
        return buildServerType == this.buildServerType;
    }

    public abstract boolean testConnection(String host, String username, String password);
    public abstract void login(String host, String username, String password);
    public abstract BuildServer toBuildServerModel();
    public abstract String downloadBuildLog(String buildId) throws Exception;
    public abstract void saveBuildLogTo(String buildId, File file) throws Exception;
    public abstract void logout();

    protected String readFile(File file) throws Exception {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        return result.toString();
    }
}
