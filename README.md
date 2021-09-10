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