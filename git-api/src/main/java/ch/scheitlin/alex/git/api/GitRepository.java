package ch.scheitlin.alex.git.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

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
}
