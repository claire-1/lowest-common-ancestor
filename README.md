# Software Engineering Module
## Lowest Common Ancestor Part 1
Part 1 is implemented separately in the `lowest-common-ancestor-v1 directory`. The most up-to-date version is on the `master` branch. Its functionality is shown in the `lowest-common-ancestor` directory as well, but I kept a separate directory for v1 because the implementation changed a lot for directed acyclic graphs. 

## Lowest Common Ancestor Part 2
Part 2 was implemented on the branch `v2` in the directory `lowest-common-ancestor`, which was then merged with `master`. The most up-to-date version is on the `master` branch.
This part allows for you to specify two descendants to find the LCA of because directed acyclic graphs can have multiple LCAs at a time and allowing for more descendants gets very complicated very quickly. 

### Requirements for Lowest Common Ancestor
- Java 8
- JUnit 5
- Maven
- Docker (optional)

### Running Tests for Lowest Common Ancestor
To run tests in the `lowest-common-ancestor-v1` directory or in the `lowest-common-ancestor` directory type: `mvn test` 

Type `mvn clean` before `mvn test` if you have previously run another maven project and are running into issues with `mvn test`.

To run the tests using Docker in the `lowest-common-ancestor-v1` directory or in the `lowest-common-ancestor` directory type:
- `sudo docker build . --tag lca`
- `sudo docker run lca`

### Lowest Common Ancestor Sources
Basic Node class: https://stackoverflow.com/questions/3522454/java-tree-data-structure \
Examples of LCA implementations: https://www.careercup.com/question?id=13437666 and https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/ \
How to use Java DirectedAcyclicGraph library:         // https://www.programcreek.com/java-api-examples/?code=taboola/taboola-cronyx/taboola-cronyx-master/taboola-cronyx/src/main/java/com/taboola/cronyx/impl/StdNameAndGroupGraphValidator.java \
About NaiveLCAFinder in Java https://jgrapht.org/javadoc/org/jgrapht/alg/lca/NaiveLCAFinder.html
and https://www.codota.com/code/java/methods/org.jgrapht.alg.DijkstraShortestPath/findPathBetween \
Maven Dockerfile: https://hub.docker.com/_/maven

## Biogrpahy of an Influencal Person in Software Engineering
My biography of Radia Perlman is in `SWEBiographyOfRadiaPerlman.pdf` on the master branch. I have included my sources in the footnotes of the paper. 

## Github Metrics Code
The code to get metrics from Github and use data visualization to display them can be found here: https://github.com/claire-1/github-metrics.git
