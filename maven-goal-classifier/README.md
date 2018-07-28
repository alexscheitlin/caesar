# Maven Goal Classifier

> _Classifies a maven goal into one of 13 categories._

The categories originate from a research paper analyzing 34,182 failing builds (out of 349 open source projects and 418 projects of ING Nederland.) The paper can be found [here](ref/paper.pdf) and the taxonomy containing the categories [here](ref/taxonomy.pdf).

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