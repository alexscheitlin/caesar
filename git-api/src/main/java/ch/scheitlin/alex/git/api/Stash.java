package ch.scheitlin.alex.git.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

class Stash {
    /**
     * Stashes all modified tracked files and staged changes.
     *
     * @param git the git object to interact with the specified git repository
     * @return the reference to the stash object
     * @throws GitAPIException
     */
    static RevCommit stashTrackedAndStaged(Git git) throws GitAPIException {
        RevCommit stash = git.stashCreate().call();
        return stash;
    }
}
