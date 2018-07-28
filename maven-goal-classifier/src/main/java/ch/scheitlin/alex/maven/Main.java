package ch.scheitlin.alex.maven;

public class Main {
    public static void main(String[] args) {
        String[] compilationGoals = null;
        String[] testingGoals = null;
        String[] dependencyGoals = null;

        try {
            compilationGoals = getCompilationGoals();
            testingGoals = getTestingGoals();
            dependencyGoals = getDependencyGoals();
        } catch (Exception e) {
            System.out.println("Could not read goals from configuration.");
        }

        System.out.println("Compilation goals:");
        for (String compilation : compilationGoals) {
            System.out.println("\t" + compilation);
        }

        System.out.println();
        System.out.println("Testing goals:");
        for (String testing : testingGoals) {
            System.out.println("\t" + testing);
        }

        System.out.println();
        System.out.println("Dependency goals:");
        for (String dependency : dependencyGoals) {
            System.out.println("\t" + dependency);
        }

        System.out.println();
        try {
            String failedGoal = "maven-compiler-plugin:compile";
            Classifier classifier = new Classifier();
            System.out.println(classifier.classify(failedGoal));
        } catch (Exception e) {
            System.out.println("Could not read goals from configuration.");
        }
    }

    private static String[] getCompilationGoals() throws Exception {
        return Config.readConfig().compilation;
    }

    private static String[] getTestingGoals() throws Exception {
        return Config.readConfig().testing;
    }

    private static String[] getDependencyGoals() throws Exception {
        return Config.readConfig().dependency;
    }
}
