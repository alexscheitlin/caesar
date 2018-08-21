# Maven Goal Log Parser

> _Parses the log of a Maven goal execution and maps information about errors and in which file and on which line and column they happened to the [error-model](../error-model)._

The parser currently supports the following Maven goals and errors:

- maven-compiler-plugin-compile
- Could not resolve dependencies
- maven-surefire-plugin-test

Some parsers require to either parse the log of the executed goal, the build summary at the end of the maven build log, or both thos logs.

To add a parser for additional Maven goal logs, do the following:

- Create a new class extending the `GoalParser`. Adhere to the following naming convention: The class name consists of the plugin and goal name (e.g _maven-compiler-plugin:compile_ becomes to `MavencompilerCompile`, omit the trailing _plugin_ of the plugin name).
- Add a new instance of the parser class to the `goalParserList` of the class `MavenGoalLogParser`.
- Write the parser following the example of the `MavencompilerCompile` class.
- Write tests for the new parser following the example of the tests for `MavencompilerCompile`.
- Extend the list above with the Maven goal that is supported by the new parser.
