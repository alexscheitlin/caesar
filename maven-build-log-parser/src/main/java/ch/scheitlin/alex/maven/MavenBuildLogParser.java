package ch.scheitlin.alex.maven;

import ch.scheitlin.alex.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.List;

public class MavenBuildLogParser {

    // regular expressions for project scan (scanning for module names)
    private static final String PROJECT_SCAN_START =
            "^\\[INFO\\] Scanning for projects\\.\\.\\.$";
    public static final String PROJECT_SCAN_IGNORE_1 =
            "^Downloading: .*$";
    public static final String PROJECT_SCAN_IGNORE_2 =
            "^Downloaded: .*$";
    private static final String PROJECT_SCAN_STEP =
            "^\\[INFO\\] ------------------------------------------------------------------------$";
    private static final String PROJECT_SCAN_REACTOR =
            "^\\[INFO\\] Reactor Build Order:$";
    public static final String PROJECT_SCAN_IGNORE_3 =
            "^\\[INFO\\]\\s*$";
    private static final String PROJECT_SCAN_MODULE =
            "^\\[INFO\\] ([\\S| ]*)$";
    private static final String PROJECT_SCAN_END =
            "^\\[INFO\\] ------------------------------------------------------------------------$";

    // regular expressions for modules scan
    private static final String MODULE_BUILDING_BEFORE = "^\\[INFO\\] ------------------------------------------------------------------------$";
    private static final String MODULE_BUILDING = "^\\[INFO\\] Building (.*) (.*)$";
    private static final String MODULE_BUILDING_AFTER = "^\\[INFO\\] ------------------------------------------------------------------------$";
    private static final String MODULES_END_1 = "^\\[INFO\\] BUILD FAILURE$";
    private static final String MODULES_END_2 = "^\\[INFO\\] BUILD SUCCESS$";
    private static final String MODULES_END_3 = "^\\[INFO\\] Reactor Summary:$";

    // regular expressions for reactor summary
    private static final String REACTOR_SUMMARY_START =
            "^\\[INFO\\] Reactor Summary:$";
    public static final String REACTOR_SUMMARY_IGNORE =
            "^\\[INFO\\]$";
    private static final String REACTOR_SUMMARY_MODULE =
            "^\\[INFO\\] ([\\S| ]*) \\.* (SUCCESS|SKIPPED|FAILURE)(?: \\[(.*s)\\])?$";
    private static final String REACTOR_SUMMARY_END =
            "^\\[INFO\\] ------------------------------------------------------------------------$";

    // regular expressions for goals
    private static final String GOAL = "^\\[INFO\\] --- (.*):(.*):(.*) \\((.*)\\) @ (.*) ---$";

    // regular expressions for build information
    private static final String BUILD_INFORMATION_START = "^\\[INFO\\] BUILD (SUCCESS|FAILURE)$";
    private static final String BUILD_INFORMATION_FAILED_GOAL = "^\\[ERROR\\] Failed to execute goal (.*):(.*):(.*):(.*) \\((.*)\\) on project (.*): (.*)$";
    private static final String BUILD_INFORMATION_DEPENDENCY_ERROR = "\\[ERROR\\] Failed to execute goal on project .*: (Could not resolve dependencies) .*$";

    private int currentLine;

    /**
     * Parses a maven build log.
     *
     * @param log the maven build log to parse.
     * @return a MavenBuild object containing the parsed information
     */
    public static MavenBuild parse(String log) {
        MavenBuildLogParser parser = new MavenBuildLogParser();
        return parser.parseMavenLog(log);
    }

    private MavenBuildLogParser() {
        this.currentLine = 0;
    }

    /**
     * Parses a maven build log line by line. Information is extracted form the following four main sections:
     * - reactor build order
     * - module building
     * - reactor summary
     * - build summary
     *
     * @param log the maven build log to parse
     * @return a MavenBuild object containing the parsed information
     */
    private MavenBuild parseMavenLog(String log) {
        String[] lines = log.split("\n");

        // get module names from "reactor build order" section
        List<MavenModule> modules = parseReactorBuildOrder(lines);

        // get module goals from "module building" section
        List<MavenModule> moduleBuilds = parseModuleBuildings(lines);

        // add all information from "module building" section to the module list
        for (int i = 0; i < modules.size(); i++) {
            // stop if there is no more built module
            // this is the case if an error happened in one module and all following modules are not built
            if (i >= moduleBuilds.size()) {
                break;
            }

            MavenModule nameModule = modules.get(i);
            MavenModule buildModule = moduleBuilds.get(i);

            if (nameModule.getName().equals(buildModule.getName())) {
                nameModule.setVersion(buildModule.getVersion());
                nameModule.setGoals(buildModule.getGoals());
            } else {
                System.out.println("The module names do not match!");
                System.out.println("\tModule found in 'reactor build order' section: " + nameModule.getName());
                System.out.println("\tModule found in 'module building' section: " + buildModule.getName());
            }
        }

        // if there is only one module the 'reactor build order' does not exist and thus no module names where caught
        // (modules being empty) and only one module was built
        if (modules.size() == 0 && moduleBuilds.size() == 1) {
            modules = moduleBuilds;
        }

        // get module status and duration form "reactor summary" section
        List<MavenModule> summaryModules = parseReactorSummary(lines);

        // add all information from "reactor summary" section to the module list
        // only if the maven build contains a "reactor summary" section (= has more than one module)
        if (modules.size() > 1) {
            if (modules.size() != summaryModules.size()) {
                System.out.println("The number of modules do not match!");
                System.out.println("\tNumber of modules found in 'reactor build order' section: " + modules.size());
                System.out.println("\tNumber of modules found in 'reactor summary' section: " + summaryModules.size());
            }

            for (int i = 0; i < modules.size(); i++) {
                MavenModule nameModule = modules.get(i);
                MavenModule summaryModule = summaryModules.get(i);

                if (nameModule.getName().equals(summaryModule.getName())) {
                    nameModule.setStatus(summaryModule.getStatus());
                    nameModule.setDuration(summaryModule.getDuration());
                } else {
                    System.out.println("The module names do not match!");
                    System.out.println("\tModule found in 'reactor build order' section: " + nameModule.getName());
                    System.out.println("\tModule found in 'reactor summary' section: " + summaryModule.getName());
                }
            }
        }

        // get build information from "build summary" section
        MavenBuild build = parseBuildSummary(lines);

        // add all previously parsed module information to the maven build
        build.setModules(modules);

        // set module status equal to build status if this is a single module build
        if (modules.size() == 1) {
            modules.get(0).setStatus(MavenModuleStatus.valueOf(build.getStatus().toString()));
        }

        // get lines of failed goal
        if (build.getFailedGoal() != null) {
            for (MavenModule module : build.getModules()) {
                for (MavenGoal goal : module.getGoals()) {
                    MavenGoal failedGoal = build.getFailedGoal();
                    if (goal.getPlugin().equals(failedGoal.getPlugin()) &&
                            goal.getVersion().equals(failedGoal.getVersion()) &&
                            goal.getName().equals(failedGoal.getName())) {
                        build.getFailedGoal().setLines(goal.getLines());
                    }
                }
            }
        }

        return build;
    }

    /**
     * Parses the "Reactor Build Order" section of a maven build log.
     *
     * @param lines the maven build log lines to parse
     * @return a list of modules just containing the module names of the maven build
     */
    private List<MavenModule> parseReactorBuildOrder(String[] lines) {
        // prepare regex matchers
        RegexMatcher startMatcher = new RegexMatcher(PROJECT_SCAN_START);
        RegexMatcher stepMatcher = new RegexMatcher(PROJECT_SCAN_STEP);
        RegexMatcher ignoreMatcher1 = new RegexMatcher(PROJECT_SCAN_IGNORE_1);
        RegexMatcher ignoreMatcher2 = new RegexMatcher(PROJECT_SCAN_IGNORE_2);
        RegexMatcher reactorMatcher = new RegexMatcher(PROJECT_SCAN_REACTOR);
        RegexMatcher ignoreMatcher3 = new RegexMatcher(PROJECT_SCAN_IGNORE_3);
        RegexMatcher moduleMatcher = new RegexMatcher(PROJECT_SCAN_MODULE);
        RegexMatcher endMatcher = new RegexMatcher(PROJECT_SCAN_END);

        // search "reactor build order" section
        // the section where maven scans the project and all lists module names
        List<String> reactorBuildOrderLines = new ArrayList<String>();
        List<MavenModule> modules = new ArrayList<MavenModule>();
        boolean hasFoundStart = false;
        boolean hasFoundReactorStatement = false;
        boolean hasStepPassed = false;
        for (int i = 0; i < lines.length; i++) {
            // catch start of project scanning
            if (!hasFoundStart) {
                if (startMatcher.matches(lines[i])) {
                    reactorBuildOrderLines.add(lines[i]);
                    hasFoundStart = true;
                }
                // go to next line as no more information is needed from either
                // - all lines before the starting line
                // - the just found starting line
                continue;
            }

            // ignore downloading lines
            if (ignoreMatcher1.matches(lines[i]) || ignoreMatcher2.matches(lines[i])) {
                continue;
            }

            // add every line after start was found
            reactorBuildOrderLines.add(lines[i]);

            // catch intermediate step
            if (!hasStepPassed) {
                if (stepMatcher.matches(lines[i])) {
                    hasStepPassed = true;
                }

                // go to next line as the reactor statement and end will follow after the intermediate step
                continue;
            }

            // catch end of project scanning and exit loop
            if (endMatcher.matches(lines[i])) {
                // store current line if 'reactor build order' was found
                if (modules.size() != 0) {
                    this.currentLine = i;
                }
                break;
            }

            // look for the reactor statement
            if (!hasFoundReactorStatement) {
                if (reactorMatcher.matches(lines[i])) {
                    hasFoundReactorStatement = true;
                    continue;
                }
            } else {
                if (ignoreMatcher3.matches(lines[i])) {
                    continue;
                }

                // parse module names
                if (moduleMatcher.matches(lines[i])) {
                    MavenModule module = new MavenModule(moduleMatcher.extractComponentsSilently(lines[i])[0]);
                    modules.add(module);
                } else {
                    if (!ignoreMatcher3.matches(lines[i])) {
                        // every line here occurs after the intermediate step but before the end and was not caught as a
                        // module
                        System.out.println("Missed module: " + lines[i]);
                    }
                }
            }
        }

        // print original "Reactor Build Order" section
        /*
        System.out.println("Original 'Reactor Build Order' section:");
        for (String line : reactorBuildOrderLines) {
            System.out.println(line);
        }
        System.out.println();
        */

        // print module names
        /*
        if (modules.size() > 0) {
            System.out.println("The project has the following " + modules.size() + " modules:");
            for (MavenModule module : modules) {
                System.out.println("\t" + module.name);
            }
        } else {
            System.out.println("The project has only one module.");
            this.currentLine = 0;
        }
        */

        return modules;
    }

    /**
     * Parses the "Module Building" section of a maven build log.
     *
     * @param lines the maven build log lines to parse
     * @return a list of modules containing the module names of the maven build and their goals
     */
    private List<MavenModule> parseModuleBuildings(String[] lines) {
        // prepare patterns
        RegexMatcher beforeStartMatcher = new RegexMatcher(MODULE_BUILDING_BEFORE);
        RegexMatcher startMatcher = new RegexMatcher(MODULE_BUILDING);
        RegexMatcher afterStartMatcher = new RegexMatcher(MODULE_BUILDING_AFTER);
        RegexMatcher goalMatcher = new RegexMatcher(GOAL);
        RegexMatcher endMatcher1 = new RegexMatcher(MODULES_END_1);
        RegexMatcher endMatcher2 = new RegexMatcher(MODULES_END_2);
        RegexMatcher endMatcher3 = new RegexMatcher(MODULES_END_3);

        // search "module building" section
        // the section where maven builds the modules by executing the maven goals
        List<MavenModule> modules = new ArrayList<MavenModule>();
        List<List<String>> moduleLines = new ArrayList<List<String>>();
        MavenModule currentModule = null;
        MavenGoal currentGoal = null;
        boolean hasFoundStartOfFirstModule = false;
        for (int i = this.currentLine; i < lines.length; i++) {
            // catch start of a module building section
            if (startMatcher.matches(lines[i])) {
                if (beforeStartMatcher.matches(lines[i-1]) && afterStartMatcher.matches(lines[i+1])) {
                    String[] components = startMatcher.extractComponentsSilently(lines[i]);
                    currentModule = new MavenModule(components[0]);
                    currentModule.setVersion(components[1]);
                    modules.add(currentModule);
                    moduleLines.add(new ArrayList<String>());
                    moduleLines.get(moduleLines.size() - 1).add(lines[i - 1]);

                    // goal of last module ends
                    currentGoal = null;

                    hasFoundStartOfFirstModule = true;
                }
            }

            if (!hasFoundStartOfFirstModule) {
                continue;
            }

            // catch end of "module building" section (containing all module buildings)
            if (endMatcher1.matches(lines[i]) || endMatcher2.matches(lines[i]) || endMatcher3.matches(lines[i])) {
                this.currentLine = i - 1;
                break;
            }

            // add all lines to the current module (just for debugging purpose)
            // not actually stored to the module
            moduleLines.get(moduleLines.size() - 1).add(lines[i]);

            // catch goal within a module building section
            if (goalMatcher.matches(lines[i])) {
                String[] components = goalMatcher.extractComponentsSilently(lines[i]);
                currentGoal = new MavenGoal();
                currentGoal.setPlugin(components[0]);
                currentGoal.setVersion(components[1]);
                currentGoal.setName(components[2]);
                currentGoal.setInformation(components[3]);
                currentGoal.setModule(components[4]);
                currentModule.addGoal(currentGoal);
            }

            // store lines of a goal
            if (currentGoal != null) {
                currentGoal.addLine(lines[i]);
            }
        }

        /*
        System.out.println("The following " + modules.size() + " module buildings were found:");
        for (int i = 0; i < modules.size(); i++) {
            // print module name and version
            System.out.println("\t" + modules.get(i).name + " | " + modules.get(i).version);

            // print module lines
            for (int j = 0; j < moduleLines.get(i).size(); j++) {
                //System.out.println("\t\t" + moduleLines.get(i).get(j));
            }

            // print goals of module
            for (int k = 0; k < modules.get(i).goals.size(); k++) {
                // print name of goal
                System.out.println("\t\t" + modules.get(i).goals.get(k).toString());

                // print goal lines
                for (String line : modules.get(i).goals.get(k).getLines()) {
                    System.out.println("\t\t\t" + line);
                }
            }
        }
        */

        return modules;
    }

    /**
     * Parses the "Reactor Summary" section of a maven build log.
     *
     * @param lines the maven build log lines to parse
     * @return a list of modules containing the module names of the maven build, their status, and duration
     */
    private List<MavenModule> parseReactorSummary(String[] lines) {
        // prepare patterns
        RegexMatcher startMatcher = new RegexMatcher(REACTOR_SUMMARY_START);
        RegexMatcher moduleMatcher = new RegexMatcher(REACTOR_SUMMARY_MODULE);
        RegexMatcher ignoreMatcher = new RegexMatcher(REACTOR_SUMMARY_IGNORE);
        RegexMatcher endMatcher = new RegexMatcher(REACTOR_SUMMARY_END);

        // search "reactor summary" section
        // the section where maven lists all modules, their build status, and duration
        List<MavenModule> modules = new ArrayList<MavenModule>();
        List<String> reactorSummaryLines = new ArrayList<String>();
        boolean hasFoundStart = false;
        for (int i = this.currentLine; i < lines.length; i++) {
            // catch start of reactor summary
            if (!hasFoundStart) {
                if (startMatcher.matches(lines[i])) {
                    reactorSummaryLines.add(lines[i - 1]);
                    reactorSummaryLines.add(lines[i]);
                    hasFoundStart = true;
                }
                continue;
            }

            // add every line after start was found
            reactorSummaryLines.add(lines[i]);

            // catch end of reactor summary and exit loop
            if (endMatcher.matches(lines[i])) {
                this.currentLine = i;
                break;
            }

            // catch module statements
            if (moduleMatcher.matches(lines[i])) {
                String[] components = moduleMatcher.extractComponentsSilently(lines[i]);
                MavenModule module = new MavenModule(components[0]);
                module.setStatus(MavenModuleStatus.valueOf(components[1]));
                module.setDuration(components[2]);
                modules.add(module);
            } else {
                if (!ignoreMatcher.matches(lines[i])) {
                    // every line here occurs after the reactor summary title but before the end and was not caught as a
                    // module
                    System.out.println("Missed module: " + lines[i]);
                }
            }
        }

        /*
        // print original ractor summary
        System.out.println("Complete Reactor Summary:");
        for (String line : reactorSummaryLines) {
            System.out.println(line);
        }

        // print modules
        System.out.println("Modules:");
        for (int i = 0; i < modules.size(); i++) {
            System.out.println("\t" + modules.get(i).status +
                    " " + modules.get(i).name +
                    (modules.get(i).duration != null ? " - " + modules.get(i).duration : ""));
        }
        */

        return modules;
    }

    /**
     * Parses the "Build Summary" section of a maven build log.
     *
     * @param lines the maven build log lines to parse
     * @return a MavenBuild object
     */
    private MavenBuild parseBuildSummary(String[] lines) {
        // prepare patterns
        RegexMatcher startMatcher = new RegexMatcher(BUILD_INFORMATION_START);
        RegexMatcher failedGoalMatcher = new RegexMatcher(BUILD_INFORMATION_FAILED_GOAL);
        RegexMatcher dependencyErrorMatcher = new RegexMatcher(BUILD_INFORMATION_DEPENDENCY_ERROR);

        // search "build summary" section
        // the section where maven indicates the build status, total time, finish date, used memory and possible errors
        MavenBuildStatus buildStatus = null;
        MavenGoal failedGoal = null;
        String errorMessage = null;
        boolean hasFoundStart = false;
        List<String> buildSummaryLines = new ArrayList<String>();
        for (int i = this.currentLine; i < lines.length; i++) {
            // catch start
            if (!hasFoundStart) {
                if (startMatcher.matches(lines[i])) {
                    buildStatus = MavenBuildStatus.valueOf(startMatcher.extractComponentsSilently(lines[i])[0]);
                    buildSummaryLines.add(lines[i - 1]);
                    buildSummaryLines.add(lines[i]);
                    hasFoundStart = true;
                }

                continue;
            }

            // add every line after start was found
            buildSummaryLines.add(lines[i]);

            if (buildStatus == MavenBuildStatus.FAILURE) {
                // catch failed goal
                if (failedGoalMatcher.matches(lines[i])) {
                    String[] components = failedGoalMatcher.extractComponentsSilently(lines[i]);
                    failedGoal = new MavenGoal();
                    failedGoal.setVersion(components[0]);
                    failedGoal.setPlugin(components[1]);
                    failedGoal.setVersion(components[2]);
                    failedGoal.setName(components[3]);
                    failedGoal.setInformation(components[4]);
                    failedGoal.setModule(components[5]);
                    failedGoal.setMessage(components[6]);

                    /*
                    System.out.println("\tVendor:\t\t\t" + failedGoal.getVendor());
                    System.out.println("\tPlugin:\t\t\t" + failedGoal.getPlugin());
                    System.out.println("\tVersion:\t\t" + failedGoal.getVersion());
                    System.out.println("\tGoal:\t\t\t" + failedGoal.getName());
                    System.out.println("\tInformation:\t" + failedGoal.getInformation());
                    System.out.println("\tModule:\t\t\t" + failedGoal.getModule());
                    System.out.println("\tMessage:\t\t" + failedGoal.getMessage());
                    */
                }

                // catch error message
                if (dependencyErrorMatcher.matches(lines[i])) {
                    String[] components = dependencyErrorMatcher.extractComponentsSilently(lines[i]);

                    errorMessage = components[0];
                }
            }
        }
        return new MavenBuild(buildStatus, failedGoal, errorMessage, buildSummaryLines);
    }
}
