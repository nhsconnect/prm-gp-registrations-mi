FROM openjdk:17.0.3-jdk-buster
RUN mkdir /home/spring
RUN groupadd --gid 102 spring
RUN useradd --home-dir /home/spring --uid 1000 --gid 102 --shell /bin/bash spring
RUN usermod -a -G spring spring
USER spring
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
