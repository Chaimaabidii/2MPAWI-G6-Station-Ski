FROM openjdk:17-jdk-alpine
EXPOSE 8087
ADD target/2MPAWI-G6-Station-Ski.war 2MPAWI-G6-Station-Ski.war
ENTRYPOINT ["java","-jar","/2MPAWI-G6-Station-Ski.war"]
