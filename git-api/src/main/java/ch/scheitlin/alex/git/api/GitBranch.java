package ch.scheitlin.alex.git.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.IOException;

class GitBranch {
    /**
     * Gets the current branch the HEAD points to.
     *
     * @param git the git object to interact with the specified git repository
     * @return the branch name
     * @throws IOException
     */
    static String getCurrent(Git git) throws IOException {
        return git.getRepository().getBranch();
    }

    /**
     * Creates a new branch starting at the specified commit.
     *
     * @param git        the git object to interact with the specified git repository
     * @param commit     the commit id to start from
     * @param branchName the name of the new branch
     * @throws IOException
     * @throws GitAPIException
     */
    static void createFromCommit(Git git, String commit, String branchName) throws IOException, GitAPIException {
        ObjectId commitId = ObjectId.fromString(commit);
        RevWalk revWalk = new RevWalk(git.getRepository());
        RevCommit revCommit = revWalk.parseCommit(commitId);
        revWalk.close();
        git.checkout()
                .setCreateBranch(true)
                .setName(branchName)
                .setStartPoint(revCommit)
                .call();
    }

    /**
     * Checks out the specified branch.
     *
     * @param git        the git object to interact with the specified git repository
     * @param branchName the name of the branch to checkout
     * @throws GitAPIException
     */
    static void checkout(Git git, String branchName) throws GitAPIException {
        git.checkout()
                .setName(branchName)
                .call();
    }
}
