package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.build.model.Error;
import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Requires to parse the build summary section of the maven build log.
 */
public class CouldNotResolveDependencies extends GoalParser {
    private final String ERROR = "^\\[ERROR\\] Failed to execute goal on project .*: (Could not resolve dependencies) for project .*: (.*)$";
    private final String ERROR_MESSAGE = "^(Could not find artifact) (.*) in .*$";
    private final String ARTIFACT = "^(.*):(.*):.*:(.*)$";

    public CouldNotResolveDependencies() {
        super( "Could not resolve dependencies");
    }

    @Override
    public List<Error> parseLog(String log) {
        // prepare matchers for regular expressions
        RegexMatcher errorMatcher = new RegexMatcher(ERROR);
        RegexMatcher errorMessageMatcher = new RegexMatcher(ERROR_MESSAGE);
        RegexMatcher artifactMatcher = new RegexMatcher(ARTIFACT);

        List<Error> errors = new ArrayList<Error>();
        for (String line : log.split("\n")) {
            // search all error lines for failed dependency
            if (errorMatcher.matches(line)) {
                String[] errorComponents = errorMatcher.extractComponentsSilently(line);

                String errorMessage = errorComponents[0];

                // check whether there was an issues with the artifact
                if (errorMessageMatcher.matches(errorComponents[1])) {
                    String[] errorMessageComponents = errorMessageMatcher.extractComponentsSilently(errorComponents[1]);

                    errorMessage = errorMessageComponents[0];
                    String artifact = errorMessageComponents[1];

                    if (artifactMatcher.matches(artifact)) {
                        String[] artifactComponents = artifactMatcher.extractComponentsSilently(artifact);

                        String groupId = artifactComponents[0];
                        String artifactId = artifactComponents[1];
                        String version = artifactComponents[2];

                        errorMessage += ": " + groupId + ":" + artifactId + ":" + version;

                        // check whether the the artifact exists in the local maven repository
                        boolean doesGroupExist = LocalMavenRepositoryReader.doesGroupExist(groupId);
                        if (!doesGroupExist) {
                            String nonExistingSubGroup = LocalMavenRepositoryReader.getNonExistingSubGroup(groupId);

                            if (nonExistingSubGroup == null) {
                                // folder for group exists but there is no version
                                errorMessage += " (group "+ groupId + " is locally not available)";
                            } else {
                                // folder for group does not exist
                                errorMessage += " (sub group '" + nonExistingSubGroup + "' is locally not available)";
                            }
                            errorMessage += "\nPlease check why this group can not be found on maven central or in any of the defined" +
                                    " repositories in the pom.xml file. Does the <groupId> really exist?";

                        } else {
                            boolean doesArtifactExist = LocalMavenRepositoryReader.doesArtifactExist(groupId, artifactId);
                            if (!doesArtifactExist) {
                                String[] artifacts = LocalMavenRepositoryReader.getArtifactsOfGroup(groupId);
                                String artifactText = "";
                                if (artifacts.length != 0) {
                                    artifactText = "Locally, only the following artifacts are available: ";
                                    for (String a : artifacts) {
                                        artifactText += a + ", ";
                                    }
                                    artifactText = artifactText.substring(0, artifactText.length() - 2);
                                    artifactText += "\n";
                                }
                                errorMessage += " (artifact '" + artifactId + "' is locally not available)";
                                errorMessage += "\nPlease check why this artifact can not be found on maven central or in any of the defined" +
                                        " repositories in the pom.xml file. Does the the <artifactId> really exist?\n" +
                                        artifactText +
                                        "Check all available artifacts of this group on maven central: " +
                                        "https://mvnrepository.com/artifact/" + groupId;

                            } else {
                                boolean doesVersionExist = LocalMavenRepositoryReader.doesVersionExist(groupId, artifactId, version);
                                if (!doesVersionExist) {
                                    String[] versions = LocalMavenRepositoryReader.getArtifactVersions(groupId, artifactId);
                                    String versionText = "";
                                    if (versions.length != 0) {
                                        versionText = "Locally, only the following versions are available: ";
                                        for (String v : versions) {
                                            versionText += v + ", ";
                                        }
                                        versionText = versionText.substring(0, versionText.length() - 2);
                                        versionText += "\n";
                                    }
                                    errorMessage += " (version '" + version + "' is locally not available)";
                                    errorMessage += "\nPlease check why this version can not be found on maven central or in any of the defined" +
                                            " repositories in the pom.xml file. Does the <version> really exist?\n" +
                                            versionText +
                                            "Check all available versions of this artifact on maven central: " +
                                            "https://mvnrepository.com/artifact/" + groupId + "/" + artifactId;

                                } else {
                                    errorMessage += "\nEven though the artifact is available locally the build server could not find it.";
                                    errorMessage += "\nPlease check if this version of the artifact really is available in one of the" +
                                            " specified repositories in the pom.xml file.";
                                }
                            }
                        }
                    }
                }

                Error error = new Error(null, "pom.xml", 0, 0, errorMessage);

                errors.add(error);
                continue;
            }
        }
        return errors;
    }
}
