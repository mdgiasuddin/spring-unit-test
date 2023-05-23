FROM openjdk:8-jdk-alpine
VOLUME /main-app-unit-test
ADD target/spring-unit-test-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9252
ENTRYPOINT ["java", "-jar","/app.jar"]
