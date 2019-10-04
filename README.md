# software-engineering-module
## Lowest Common Ancestor Part 1
TODO: say which commit is the last commit for this part once part 2 is done
This part was done on the master branch. \
Requirements: 
- Java 8
- JUnit 5
- Maven

To run tests in the lowest-common-ancestor-v1 directory type: `mvn test` 

Type `mvn clean` before `mvn test` if you have previously run another maven project and are running into issues with `mvn test`.

To run the tests using Docker in the lowest-common-ancestor-v1 directory type:
- `sudo docker build . --tag LCA1`
- `sudo docker run LCA1`

Sources: \
Basic Node class: https://stackoverflow.com/questions/3522454/java-tree-data-structure \
Examples of LCA implementations: https://www.careercup.com/question?id=13437666 and https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/ \
Maven Dockerfile: https://hub.docker.com/_/maven