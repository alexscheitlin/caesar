package ch.scheitlin.alex.git.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class Stage {
    /**
     * Stages all files of a git repository (aka. 'git add .').
     *
     * @param git the git object to interact with the specified git repository
     * @throws GitAPIException
     */
    static void stageAllFiles(Git git) throws GitAPIException {
        git.add().addFilepattern(".").call();
    }
}
