# libDB
Web application for managing a state-wide library system. Uses Spring Boot in a gradle project as a backend server which also serves the frontend's static web content.

## Required software

* [Java JDK/JRE](https://www.oracle.com/technetwork/java/javase/downloads/index.html) - Your java `jre1.8.*/lib` folder will be missing a `tools.jar` file which Gradle needs to build the application. The `tools.jar` can be found in your `Java/jdk1.8.*/lib` folder. Just copy it over to the jre lib folder.
* [Gradle](https://gradle.org/install/) - Follow the installation instructions and make sure you edit your environment variables.

## Running the application

1. Build the application using `.\gradlew build` while in the `libDB` directory.
2. Run the application by executing the generated jar file with the command: `java -jar app/build/libs/app-0.0.1.jar`
3. Navigate to `http://localhost:8080` in your browser to use the application.
