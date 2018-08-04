# Maven Goal Classifier

> _Classifies a maven goal into one of 13 categories._

The 13 categories with total 170 goals originate from a research paper analyzing 34,182 failing builds (out of 349 open source projects and 418 projects of ING Nederland.) The paper can be found [here](ref/paper.pdf) and the taxonomy containing the categories [here](ref/taxonomy.pdf).

The 13 goal categories are:

```
    CLEAN,
    VALIDATION,
    PRE_PROCESSING,
    COMPILATION,
    TESTING,
    PACKAGING,
    ANALYSIS,
    DEPLOYMENT,
    EXTERNAL_TASKS,
    DOCUMENTATION,
    RELEASE_PREPARATION,
    SUPPORT,
    DEPENDENCY
```

The categories contain the following 170 goals:

```
clean goals (1):
	- maven-clean-plugin:clean

validation goals (6):
	- duplicate-finder-maven-plugin:check
	- maven-duplicate-finder-plugin:check
	- maven-enforcer-plugin:enforce
	- lint-maven-plugin:check
	- clirr-maven-plugin:check
	- maven-sortpom-plugin:verify

pre processing goals (13):
	- android-maven-plugin:generate-sources
	- maven-resources-plugin:resources
	- maven-remote-resources-plugin:process
	- jooq-codegen-maven:generate
	- maven-plugin-plugin:helpmojo
	- xml-maven-plugin:transform
	- download-maven-plugin:wget
	- android-maven-plugin:generate-sources
	- git-commit-id-plugin:revision
	- buildnumber-maven-plugin:create
	- buildnumber-maven-plugin:create
	- mavanagaiata:commit
	- minify-maven-plugin:minify

compilation goals (21):
	- maven-jaxb2-plugin:generate
	- maven-scala-plugin:compile
	- sass-maven-plugin:update-stylesheets
	- maven-compiler-plugin:compile
	- scala-maven-plugin:compile
	- gwt-maven-plugin:compile
	- jcabi-maven-plugin:ajc
	- maven-protoc-plugin:compile
	- android-maven-plugin:ndk-build
	- gmaven-plugin:compile
	- aspectj-maven-plugin:compile
	- maven-compiler-plugin:testCompile
	- scala-maven-plugin:testCompile
	- maven-scala-plugin:testCompile
	- org.solovyev.external.javac2-maven-plugin:instrument
	- maven-processor-plugin:process
	- yuicompressor-maven-plugin:compress
	- antlr3-maven-plugin:antlr
	- antlr4-maven-plugin:antlr4
	- maven-replacer-plugin:replace
	- property-helper-maven-plugin:get

testing goals (21):
	- clojure-maven-plugin:test
	- maven-surefire-plugin:test
	- maven-surefire-plugin:test
	- scalatest-maven-plugin:test
	- clojure-maven-plugin:test-with-junit
	- android-maven-plugin:internal-pre-integration-test
	- maven-invoker-plugin:install
	- maven-surefire-plugin:test
	- maven-failsafe-plugin:verify
	- gwt-maven-plugin:test
	- android-maven-plugin:internal-integration-test
	- maven-invoker-plugin:verify
	- maven-invoker-plugin:run
	- maven-archetype-plugin:integration-test
	- android-maven-plugin:internal-integration-test
	- failsafe-maven-plugin:verify
	- maven-failsafe-plugin:integration-test
	- cassandra-maven-plugin:start
	- cassandra-maven-plugin:delete
	- gatling-maven-plugin:execute
	- There are test failures

packaging goals (23):
	- android-maven-plugin:dex
	- maven-war-plugin:exploded
	- android-maven-plugin:dex
	- appassembler-maven-plugin:assemble
	- maven-war-plugin:war
	- maven-instpkg-plugin:createSPSPackage
	- maven-assembly-plugin:single
	- maven-jar-plugin:test-jar
	- maven-jar-plugin:jar
	- build-helper-maven-plugin:attach-artifact
	- launch4j-maven-plugin:launch4j
	- jdeb:jdeb
	- maven-source-plugin:jar
	- maven-source-plugin:jar-no-fork
	- maven-bundle-plugin:bundle
	- maven-shade-plugin:shade
	- maven-archetype-plugin:jar
	- maven-assembly-plugin:attached
	- android-maven-plugin:apk
	- rpm-maven-plugin:attached-rpm
	- maven-jarsigner-plugin:sign
	- animal-sniffer-maven-plugin:check
	- maven-gpg-plugin:sign

analysis goals (20):
	- sonar-maven-plugin:sonar
	- qulice-maven-plugin:check
	- findbugs-maven-plugin:findbugs
	- findbugs-maven-plugin:check
	- maven-pmd-plugin:pmd
	- maven-checkstyle-plugin:checkstyle
	- maven-checkstyle-plugin:check
	- android-maven-plugin:lint
	- jslint-maven-plugin:jslint
	- scalastyle-maven-plugin:check
	- pitest-maven:mutationCoverage
	- license-maven-plugin:check-file-header
	- apache-rat-plugin:check
	- license-maven-plugin:check
	- maven-license-plugin:check
	- coveralls-maven-plugin:report
	- cobertura-maven-plugin:instrument
	- coveralls-maven-plugin:cobertura
	- coveralls-maven-plugin:jacoco
	- jacoco-maven-plugin:prepare-agent

deployment goals (6):
	- maven-install-plugin:install
	- maven-bundle-plugin:install
	- maven-install-plugin:install-file
	- maven-deploy-plugin:deploy
	- maven-release-plugin:perform
	- nexus-staging-maven-plugin:deploy

external tasks goals (14):
	- maven-antrun-plugin:run
	- grunt-maven-plugin:grunt
	- frontend-maven-plugin:grunt
	- cargo-maven2-plugin:start
	- frontend-maven-plugin:install-node-and-npm
	- frontend-maven-plugin:npm
	- wro4j-maven-plugin:run
	- jetty-console-maven-plugin:createconsole
	- jetty-console-maven-plugin:createconsole
	- exec-maven-plugin:java
	- sql-maven-plugin:execute
	- exec-maven-plugin:exec
	- groovy-maven-plugin:execute
	- scala-maven-plugin:script

documentation goals (11):
	- maven-enunciate-slim-plugin:docs
	- maven-javadoc-plugin:jar
	- maven-site-plugin:site
	- site-maven-plugin:site
	- sphinx-maven-plugin:generate
	- maven-site-plugin:attach-descriptor
	- maven-plugin-plugin:descriptor
	- swagger-maven-plugin:generate
	- maven-javadoc-plugin:javadoc
	- swagger-maven-plugin:generate
	- asciidoctor-maven-plugin:process-asciidoc

release preparation goals (2):
	- maven-release-plugin:prepare
	- maven-release-plugin:update-versions

support goals (22):
	- flyway-maven-plugin:migrate
	- liquibase-maven-plugin:update
	- nbm-maven-plugin:manifest
	- gmaven-plugin:generateStubs
	- scala-maven-plugin:add-source
	- codegen-maven-plugin:dal-model
	- maven-site-plugin:stage
	- latex-maven-plugin:latex
	- maven-bundle-plugin:baseline
	- build-helper-maven-plugin:reserve-network-port
	- jcp:preprocess
	- jcabi-dynamodb-maven-plugin:create-tables
	- maven-db-plugin:schema
	- android-maven-plugin:undeploy
	- maven-archetype-plugin:generate
	- apt-maven-plugin:test-process
	- properties-maven-plugin:read-project-properties
	- org.eclipse.virgo.bundlor.maven:bundlor
	- antlr-maven-plugin:generate
	- jruby-maven-plugin:jruby
	- android-maven-plugin:undeploy
	- maven-sortpom-plugin:sort

dependency goals (10):
	- Could not resolve dependencies
	- Failed to resolve classpath resources
	- maven-dependency-plugin:resolve-plugins
	- maven-dependency-plugin:purge-local-repository
	- maven-dependency-plugin:list
	- maven-dependency-plugin:copy
	- maven-dependency-plugin:unpack
	- maven-dependency-plugin:copy-dependencies
	- maven-dependency-plugin:analyze-only
	- maven-dependency-versions-check-plugin:check
```