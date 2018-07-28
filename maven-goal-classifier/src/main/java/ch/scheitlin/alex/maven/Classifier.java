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
        Config config = Config.readConfig();

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

    public MavenGoalCategory classify(String goal) {
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
            return null;
        }
    }
}
