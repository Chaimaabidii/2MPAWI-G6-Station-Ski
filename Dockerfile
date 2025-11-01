FROM openjdk:8-jdk-alpine
EXPOSE  8089
ADD target/2MPAWI-G6-Station-Ski.war 2MPAWI-G6-Station-Ski.war
ENTRYPOINT ["java", "-jar", "app.jar"]
