# Build Server Model

> _Represents the data of a build server. This includes information about projects, build configurations, and 
executed by builds._

## Purpose

The build server model is intended to represent data fetched from build servers. The goal is to map the build server 
configuration (i.e. projects and build configurations) and executed builds (including the branch) to a common model, 
no matter what build server the data originates from.

This enables CAESAR to access information about builds through this model. There is no need for CAESAR to know how to
 interact different build servers.


# UML Class Diagram

A `BuildServer` has a `BuildServerType` (currently only TeamCity is supported) and multiple `Project`s.
Every `Project` has multiple `BuildConfiguration`s that execute `Build`s which may be grouped by the branch of the 
version control system.

![Build Server Model](assets/build-server-model.png)
