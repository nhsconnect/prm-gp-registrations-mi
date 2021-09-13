# prm-gp-registrations-mi

GP Registrations MI is a service used to collect Management Information describing patient transfers in the form of JSON events sent over a RESTful API.

## Setup

### Prerequisites

- Download Java 11.0.7 [https://www.oracle.com/uk/java/technologies/javase/jdk11-archive-downloads.html]
```
macOS Installer: jdk-11.0.7_osx-x64_bin.dmg
```
*note: you will need to sign up with an Oracle account*

List your java versions
```
/usr/libexec/java_home -V 
```

Set the JAVA_HOME path in your config, eg. (zsh):
```
vim ~/.zshrc
export JAVA_HOME=`/usr/libexec/java_home -v 11`
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