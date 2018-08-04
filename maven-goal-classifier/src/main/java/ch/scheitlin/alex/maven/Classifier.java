package ch.scheitlin.alex.maven;

import java.util.Arrays;
import java.util.List;

public class Classifier {
    private List<String> cleanGoals;
    private List<String> validationGoals;
    private List<String> preProcessingGoals;
    private List<String> compilationGoals;
    private List<String> testingGoals;
    private List<String> packagingGoals;
    private List<String> analysisGoals;
    private List<String> deploymentGoals;
    private List<String> externalTasksGoals;
    private List<String> documentationGoals;
    private List<String> releasePreparationGoals;
    private List<String> supportGoals;
    private List<String> dependencyGoals;

    public Classifier() throws Exception {
        // read all goals from the configuration file
        Config config = Config.readConfig();

        // list all goals
        this.cleanGoals = Arrays.asList(config.clean);
        this.validationGoals = Arrays.asList(config.validation);
        this.preProcessingGoals = Arrays.asList(config.preProcessing);
        this.compilationGoals = Arrays.asList(config.compilation);
        this.testingGoals = Arrays.asList(config.testing);
        this.packagingGoals = Arrays.asList(config.packaging);
        this.analysisGoals = Arrays.asList(config.analysis);
        this.deploymentGoals = Arrays.asList(config.deployment);
        this.externalTasksGoals = Arrays.asList(config.externalTasks);
        this.documentationGoals = Arrays.asList(config.documentation);
        this.releasePreparationGoals = Arrays.asList(config.releasePreparation);
        this.supportGoals = Arrays.asList(config.support);
        this.dependencyGoals = Arrays.asList(config.dependency);
    }

    public String[] getCleanGoals() {
        return this.cleanGoals.toArray(new String[this.cleanGoals.size()]);
    }

    public String[] getValidationGoals() {
        return this.validationGoals.toArray(new String[this.validationGoals.size()]);
    }

    public String[] getPreProcessingGoals() {
        return this.preProcessingGoals.toArray(new String[0]);
    }

    public String[] getCompilationGoals() {
        return this.compilationGoals.toArray(new String[this.compilationGoals.size()]);
    }

    public String[] getTestingGoals() {
        return this.testingGoals.toArray(new String[0]);
    }

    public String[] getPackagingGoals() {
        return this.packagingGoals.toArray(new String[0]);
    }

    public String[] getAnalysisGoals() {
        return this.analysisGoals.toArray(new String[0]);
    }

    public String[] getDeploymentGoals() {
        return this.deploymentGoals.toArray(new String[0]);
    }

    public String[] getExternalTasksGoals() {
        return this.externalTasksGoals.toArray(new String[0]);
    }

    public String[] getDocumentationGoals() {
        return this.documentationGoals.toArray(new String[0]);
    }

    public String[] getReleasePreparationGoals() {
        return this.releasePreparationGoals.toArray(new String[0]);
    }

    public String[] getSupportGoals() {
        return this.supportGoals.toArray(new String[0]);
    }

    public String[] getDependencyGoals() {
        return this.dependencyGoals.toArray(new String[0]);
    }

    public MavenGoalCategory classify(String goal) {
        // return the category if the goal is part of any goal list
        if (this.cleanGoals.contains(goal)) {
            return MavenGoalCategory.CLEAN;
        } else if (this.validationGoals.contains(goal)) {
            return MavenGoalCategory.VALIDATION;
        } else if (this.preProcessingGoals.contains(goal)) {
            return MavenGoalCategory.PRE_PROCESSING;
        } else if (this.compilationGoals.contains(goal)) {
            return MavenGoalCategory.COMPILATION;
        } else if (this.testingGoals.contains(goal)) {
            return MavenGoalCategory.TESTING;
        } else if (this.packagingGoals.contains(goal)) {
            return MavenGoalCategory.PACKAGING;
        } else if (this.analysisGoals.contains(goal)) {
            return MavenGoalCategory.ANALYSIS;
        } else if (this.deploymentGoals.contains(goal)) {
            return MavenGoalCategory.DEPLOYMENT;
        } else if (this.externalTasksGoals.contains(goal)) {
            return MavenGoalCategory.EXTERNAL_TASKS;
        } else if (this.documentationGoals.contains(goal)) {
            return MavenGoalCategory.DOCUMENTATION;
        } else if (this.releasePreparationGoals.contains(goal)) {
            return MavenGoalCategory.RELEASE_PREPARATION;
        } else if (this.supportGoals.contains(goal)) {
            return MavenGoalCategory.SUPPORT;
        } else if (this.dependencyGoals.contains(goal)) {
            return MavenGoalCategory.DEPENDENCY;
        } else {
            return MavenGoalCategory.UNKNOWN;
        }
    }
}
