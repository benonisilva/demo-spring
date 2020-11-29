FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn package

EXPOSE 5005

#run the spring boot application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n", "-Dblabla", "-jar", "-Dspring.profiles.active=docker", "/project/target/demo-0.0.1-SNAPSHOT.jar"]


