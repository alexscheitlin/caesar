package ch.scheitlin.alex.build;

import ch.scheitlin.alex.build.model.BuildServer;
import ch.scheitlin.alex.build.model.BuildServerType;
import ch.scheitlin.alex.teamcity.api.TeamcityApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BuildServerApi {
    private Api api;
    private List<Api> apiList;

    public BuildServerApi(BuildServerType type) {
        this.apiList = new ArrayList<Api>();

        // add a new instance of every api
        this.apiList.add(new TeamcityApi());

        // get the corresponding api
        for (Api api : apiList) {
            if (api.isApiFor(type)) {
                this.api = api;
                break;
            }
        }
    }

    public boolean testConnection(String host, String username, String password) {
        return this.api.testConnection(host, username, password);
    }
    
    public void login(String host, String username, String password) {
        this.api.login(host, username, password);
    }
    
    public BuildServer toBuildServerModel() {
        return this.api.toBuildServerModel();
    }
    
    public String downloadBuildLog(String buildId) throws Exception {
        return this.api.downloadBuildLog(buildId);
    }
    
    public void saveBuildLogTo(String buildId, File file) throws Exception {
        this.api.saveBuildLogTo(buildId, file);
    }
    
    public void logout() {
        this.api.logout();
    }
}
