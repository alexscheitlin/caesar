package ch.scheitlin.alex.git.api;

import javafx.util.Pair;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Common {
    /**
     * Gets all remote urls.
     *
     * @param git the git object to interact with the specified git repository
     * @return a List with String-Pairs having the remote names as keys and the corresponding url as values
     */
    static List<Pair<String, String>> getRemoteUrls(Git git) {
        Repository repository = git.getRepository();
        Config storedConfig = repository.getConfig();
        Set<String> remoteNames = storedConfig.getSubsections("remote");

        List<Pair<String, String>> remotes = new ArrayList<Pair<String, String>>();
        for (String remoteName : remoteNames) {
            String url = storedConfig.getString("remote", remoteName, "url");
            remotes.add(new Pair<String, String>(remoteName, url));
        }
        return remotes;
    }
}
