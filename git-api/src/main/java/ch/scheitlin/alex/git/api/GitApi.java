package ch.scheitlin.alex.git.api;

import javafx.util.Pair;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.util.List;

public class GitApi {
    private Git git;

    public GitApi(String repositoryPath) throws Exception {
        File file = new File(repositoryPath);

        try {
            this.git = Git.open(file);
        } catch (Exception ex) {
            throw new Exception("Could not access the git repository at: " + repositoryPath);
        }
    }

    public List<Pair<String, String>> getRemoteUrls() {
        return Common.getRemoteUrls(this.git);
    }

    public String getCurrentBranch() throws Exception {
        try {
            return GitBranch.getCurrent(this.git);
        } catch (Exception ex) {
            throw new Exception("Could not read the current branch of the git repository.");
        }
    }

    public boolean isRepositoryClean() throws Exception {
        try {
            return GitRepository.isClean(this.git);
        } catch (Exception ex) {
            throw new Exception("Could not check whether the git repository is clean.");
        }
    }

    public void stageAllFiles() throws Exception {
        try {
            Stage.stageAllFiles(this.git);
        } catch (Exception ex) {
            throw new Exception("Could not stage files.");
        }
    }

    public String stashTrackedAndStagedFiles() throws Exception {
        try {
            return Stash.stashTrackedAndStaged(this.git).getName();
        } catch (Exception ex) {
            throw new Exception("Could not stash tracked and staged files.");
        }
    }

    public void fetchFromRemoteRepository(String username, String password) throws Exception {
        try {
            GitRepository.fetchFromRemoteRepository(this.git, username, password);
        } catch (Exception ex) {
            throw new Exception("Could not stash tracked and staged files.");
        }
    }

    public void createBranchFromCommit(String commitHash, String branchName) throws Exception {
        try {
            GitBranch.createFromCommit(this.git, commitHash, branchName);
        } catch (Exception ex) {
            throw new Exception("Could not create a new branch.");
        }
    }

    public void checkoutBranch(String branchName) throws Exception {
        try {
            GitBranch.checkout(this.git, branchName);
        } catch (Exception ex) {
            throw new Exception("Could not checkout branch: " + branchName);
        }
    }
}
