# Maven Build Log Parser

> _Parses a maven build log and maps it to the given model below._

A maven log consists of 4 main sections:
 - the "Reactor Build Order" where maven lists all modules
 ```
 [INFO] Scanning for projects...
 [INFO] ------------------------------------------------------------------------
 [INFO] Reactor Build Order:
 [INFO]
 [INFO] module-1
 [INFO] module-2
 [INFO] module-3
 [INFO] module-4
 ```
 - the "Module Building" where maven builds all modules (by executing the respective maven goals)
 ```
 [INFO] ------------------------------------------------------------------------
 [INFO] Building module-1 1.0.0-SNAPSHOT
 [INFO] ------------------------------------------------------------------------
 Downloading: http://repo.maven.apache.org/...
 Downloaded: http://repo.maven.apache.org/...
 ...
 [INFO]
 [INFO] --- maven-clean-plugin:1.0.0:clean (default-clean) @ module-1 ---
 [INFO]
 ...
 [INFO]
 [INFO] --- maven-enforcer-plugin:1.0.0:enforce (enforce-maven) @ module-1 ---
 [INFO]
 ...
 [INFO] ------------------------------------------------------------------------
 [INFO] Building module-2 1.0.0
 [INFO] ------------------------------------------------------------------------
 ...
 ```
 - the "Reactor Summary" where maven lists all modules and their build status and duration
 ```
 [INFO] ------------------------------------------------------------------------
 [INFO] Reactor Summary:
 [INFO]
 [INFO] module-1 .......................................... SUCCESS [14.283s]
 [INFO] module-2 .......................................... SUCCESS [5.214s]
 [INFO] module-3 .......................................... SUCCESS [30.706s]
 [INFO] module-4 .......................................... SUCCESS [0.375s]
 ```
 - the "Build Summary" where maven indicates the build status and gives some information about a potential build failure
 ```
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESS
 [INFO] ------------------------------------------------------------------------
 [INFO] Total time: 2:43.020s
 [INFO] Finished at: Fri Jun 15 17:47:00 CEST 2018
 [INFO] Final Memory: 41M/167M
 [INFO] ------------------------------------------------------------------------
 ```
 
 For projects having just one module the maven log looks a little different:
  - the "Reactor Build Order" does not exists
  - the "Reactor Summary" does not exist
 
This project parses the maven log and maps it to the following model:

[Maven Model](../maven-model)
