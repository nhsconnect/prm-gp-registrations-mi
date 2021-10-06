# prm-gp-registrations-mi

GP Registrations MI is a service used to collect Management Information describing patient transfers in the form of JSON events sent over a RESTful API.

## Setup

### Prerequisites

- Download Java 11 OpenJDK [https://wiki.openjdk.java.net/display/JDKUpdates/JDK11u]

List your java versions
```
/usr/libexec/java_home -V 
```

You may need to set the JAVA_HOME path in your config, eg. (zsh):
```
vim ~/.zshrc
export JAVA_HOME=`/usr/libexec/java_home`
```

Source your profile to load the changes:
```
source ~/.zshrc
```

### Running the application

```
./tasks run_local
```
To check the application is running, you can hit the /event endpoint:
```
curl -X POST -H "Content-Type: application/json" \
    -d '{"fruit": "mango"}' \
    http://localhost:8080/event
```

Or you can also hit the /health endpoint:
```
http://localhost:8080/actuator/health
```


### Running the tests and additional checks

Run unit tests
```
./tasks test_unit
```

Run integration tests
```
./tasks test_integration
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


### Troubleshooting
If you are using IntelliJ and you are unable to run the tests, 
make sure to run the tests through IntelliJ IDEA rather than Gradle 
```
Build, Execution, Deployment > Build Tools > Gradle > Run tests using: Intellij
```