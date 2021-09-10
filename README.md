# prm-gp-registrations-mi

GP Registrations MI is a service used to collect Management Information describing patient transfers in the form of JSON events sent over a RESTful API.

## Setup

### Prerequisites

- Download Java 16

List your java versions
```
/usr/libexec/java_home -V 
```

Set the JAVA_HOME path in your config, eg:
```
/usr/libexec/java_home -v 16.0.2
```

### Running the application

```
./gradlew bootRun
```

To check the application is running, you can hit the /health endpoint:
```
http://localhost:8080/actuator/health
```

### Running the tests and additional checks

Run unit tests
```
./gradlew test
```

Generate test coverage report. The report can be found in `build/reports/jacoco/test/html/index.html`
```
./gradlew jacocoTestReport
```

Run test coverage verification
```
./gradlew jacocoTestCoverageVerification
```

Run dependency check
```
./gradlew dependencyCheckAnalyze
```

Run static code analysis to detect bugs
```
./gradlew spotbugsMain && ./gradlew spotbugsTest
```