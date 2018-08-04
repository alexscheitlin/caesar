import ch.scheitlin.alex.maven.Classifier;

public class Main {
    private static final String ERROR_MESSAGE = "Could not read goals from configuration.";

    private static int counter = 0;

    public static void main(String[] args) {
        printCleanGoals();
        printValidationGoals();
        printPreProcessingGoals();
        printCompilationGoals();
        printTestingGoals();
        printPackagingGoals();
        printAnalysisGoals();
        printDeploymentGoals();
        printExternalTasksGoals();
        printDocumentationGoals();
        printReleasePreparationGoals();
        printSupportGoals();
        printDependencyGoals();

        System.out.println("Goals: " + counter);
    }

    private static void printCleanGoals() {
        String category = "clean";
        String[] goals = null;
        try {
            goals = new Classifier().getCleanGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printValidationGoals() {
        String category = "validation";
        String[] goals = null;
        try {
            goals = new Classifier().getValidationGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printPreProcessingGoals() {
        String category = "pre processing";
        String[] goals = null;
        try {
            goals = new Classifier().getPreProcessingGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printCompilationGoals() {
        String category = "compilation";
        String[] goals = null;
        try {
            goals = new Classifier().getCompilationGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printTestingGoals() {
        String category = "testing";
        String[] goals = null;
        try {
            goals = new Classifier().getTestingGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printPackagingGoals() {
        String category = "packaging";
        String[] goals = null;
        try {
            goals = new Classifier().getPackagingGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printAnalysisGoals() {
        String category = "analysis";
        String[] goals = null;
        try {
            goals = new Classifier().getAnalysisGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printDeploymentGoals() {
        String category = "deployment";
        String[] goals = null;
        try {
            goals = new Classifier().getDeploymentGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printExternalTasksGoals() {
        String category = "external tasks";
        String[] goals = null;
        try {
            goals = new Classifier().getExternalTasksGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printDocumentationGoals() {
        String category = "documentation";
        String[] goals = null;
        try {
            goals = new Classifier().getDocumentationGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printReleasePreparationGoals() {
        String category = "release preparation";
        String[] goals = null;
        try {
            goals = new Classifier().getReleasePreparationGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printSupportGoals() {
        String category = "support";
        String[] goals = null;
        try {
            goals = new Classifier().getSupportGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printDependencyGoals() {
        String category = "dependency";
        String[] goals = null;
        try {
            goals = new Classifier().getDependencyGoals();
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        printGoals(category, goals);
    }

    private static void printGoals(String category, String[] goals) {
        if (category == null || goals == null) {
            return;
        }

        System.out.println(category + " goals (" + goals.length + "):");

        for (String goal : goals) {
            System.out.println("\t- " + goal);

            counter++;
        }

        System.out.println();
    }
}
