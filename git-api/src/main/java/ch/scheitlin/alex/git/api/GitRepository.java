package ch.scheitlin.alex.git.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

class GitRepository {
    /**
     * Tells whether the git repository is clean or not.
     *
     * @param git the git object to interact with the specified git repository
     * @return {@code true} if no differences exist between the working-tree, the index, and the current HEAD,
     * {@code false} if differences do exist
     * @throws GitAPIException
     */
    static boolean isClean(Git git) throws GitAPIException {
        return git.status().call().isClean();
    }

    /**
     * Fetch branches and/or tags from the remote repository.
     *
     * @param git      the git object
     * @param username the username to authenticate to the remote repository
     * @param password the password to authenticate to the remote repository
     * @return the fetch result
     * @throws GitAPIException
     */
    static FetchResult fetchFromRemoteRepository(Git git, String username, String password) throws GitAPIException {
        UsernamePasswordCredentialsProvider credentials = new UsernamePasswordCredentialsProvider(username, password);

        return git.fetch()
                .setCredentialsProvider(credentials)
                .setCheckFetchedObjects(true)
                .call();
    }
}
