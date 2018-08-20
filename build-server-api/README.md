# Build Server API

> _Maps data from the build server to the [build-server-model](../build-server-model/README.md) and downloads build logs._

## Features

- get a list of all projects configured on the build server
- map all builds of a build configuration to the [build-server-model](../build-server-model/README.md)
- test credentials for a specific build server instance
- download a build log

## Add new Build Server

To add a new build server to the api, follow these steps:

- Create the corresponding sub-package (e.g. `teamcity.api`).
- Implement the `Api` class for the corresponding build server (e.g. `TeamcityApi`).
- Add an instance of the implemented build server to the `apiList` within the constructor of the `BuildServerApi`.

## Dependencies

- https://github.com/JetBrains/teamcity-rest-client
