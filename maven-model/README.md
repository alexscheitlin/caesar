# Maven Model

> _Represents the data of a Maven build._

A `MavenBuild` consists of at least one `MavenModule` which has at least one `MavenGoal`. The log for every executed goal is stored within the a `MavenGoal`.

If the maven build failed there should either be:
- an error message (the build failed before a module was built, thus no go was executed - e.g. 'Could not resolve dependencies')
- a failed goal (during the build of a module one of the goals fails - e.g. compilation error)

# UML Class Diagram

![Maven Model](assets/maven-model.png)
