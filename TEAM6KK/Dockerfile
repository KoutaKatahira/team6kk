FROM maven:3-eclipse-temurin-21 AS build
COPY ./TEAM6KK/ /home/app
RUN cd /home/app && mvn clean package -Dmaven.test.skip=true
FROM eclipse-temurin:21-alpine
COPY --from=build /home/app/target/TEAM9Katahira-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]