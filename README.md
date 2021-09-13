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
./tasks run_local
```

To check the application is running, you can hit the /health endpoint:
```
http://localhost:8080/actuator/health
```

### Running the tests and additional checks

Run unit tests
```
./tasks test
```

Run test coverage verification
```
./tasks test_coverage
```

Run dependency check
```
./tasks dep
```

Run code quality check
```
./tasks code_quality
```