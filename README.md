<div align="center">

![Icon](assets/icon.png)

**Command Line Interface** |
_[IntelliJ Plugin](https://github.com/alexscheitlin/build-failure-resolution-assistant-intellij-plugin)_

# Build Failure Resolution Assistant

_Helps fixing broken builds by downloading and summarizing build logs._

</div>

The Build Failure Resolution Assistant connects to a build server and shows all executed builds to the user. By selecting one of the builds the corresponding build log is downloaded, parsed, and summarized. The user then gets a summary of the build's execution like information about it's status (successful or not) or possible errors. Every failed build gets classified (e.g. dependency, compilation, or test issues) and occurred errors are listed with information about their location (file and line/column). If the user decides to fix the build failure, uncommitted changes get saved automatically and the code base causing the build failure is downloaded and checked out. The user may now debug and fix the errors, merge the applied changes to whatever branch he wants, push the changes to the remote repository the build server monitors, reapply the previously saved changes, and continue working where he stopped.

Currently the Build Failure Resolution Assistant supports projects developed with Git and Maven using TeamCity as a build server. Although the classification of build failures works for numerous types of errors, the error detection and localization is only implemented for basic errors concerning dependency issues, compilation errors, and failing tests.


_To better understand how the Build Failure Resolution Assistant is built, take a look at the modules table and the architecture image below and explore the different modules in the order they are listed in the table below._

_Built on top of this project there is a [IntelliJ Plugin](https://github.com/alexscheitlin/build-failure-resolution-assistant-intellij-plugin)._

## Modules

This project contains the following modules:

| Module | Purpose |
| --- | --- |
| [Build Server Type](build-server-type) | Represents different types of build servers (e.g. `TEAM_CITY`). Needed to set the api used to access the information on the build server and to set the parser for parsing the build log. |
| [Build Server Model](build-server-model) | Represents the data of a build server. This includes information about projects, build configurations, and executed builds. Needed to map the data from any build server to a common model to later present it to the user which then selects an executed build to get a summarization. |
| [Build Model](build-model) | Represents the data of a build of a build server. Needed to map all information out of a build log to a common model to later extract specific information about the build and the maven build log. |
| [Maven Model](maven-model) | Represents the data of a maven build.  Needed to map all information out of a maven build log to a common model to later extract specific information about the build and certain parts of the build log to locate and classify the error caused a possible build failure. |
| [Error Model](error-model) | Represents an error occurred in a particular file. Needed to map information about the location of an error (file, line, column) to a common model and present it to the user. 
| [Build Server API](build-server-api) | Maps data from the build server to the [Build Server Model](build-server-model) and downloads build logs. |
| [Git API](git-api) | Connects to a local git repository and offers basic git commands to save the current state of work before starting to fix the build failure and access the code base caused the build failure.|
| [Build Server Build Log Parser](build-server-build-log-parser) | Parses a build log of a build server and maps the data to the [Build Model](build-model). |
| [Maven Build Log Parser](maven-build-log-parser) | Parses a maven build log and maps the data to the [Maven Model](maven-model). |
| [Maven Goal Log Parser](maven-goal-log-parser) | Parses the log of a Maven goal execution and maps information about errors and in which file and on which line and column they happened to the [error-model](error-model). |
| [Maven Goal Classifier](maven-goal-classifier) | Classifies a maven goal into one of 13 categories. |
| [Build Failure Resolution Assistant](build-failure-resolution-assistant) | Assists in fixing build failures by downloading a build log, parsing and summarizing it, reporting its status and possible errors, stashing open local changes, checking out the failed version, providing hints on where possible errors happened, and let the user fix the error. |

## Architecture

The following visualization shows how the different modules interact. A more detailed overview on how the Build Failure Resolution Assistant operates is given [here](build-failure-resolution-assistant).

![Architecture](assets/architecture.png)
