FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/appstore-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
