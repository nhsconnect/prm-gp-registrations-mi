# prm-gp-registrations-mi

Download Java 16

List your java versions
```
/usr/libexec/java_home -V 
```

Set the JAVA_HOME path in your config, eg:
```
/usr/libexec/java_home -v 16.0.2
```

To check the application is running, you can hit the /health endpoint:
```
http://localhost:8080/actuator/health
```

## Setup

### Running the application
```
./gradlew bootRun
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