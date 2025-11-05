# Utiliser une image officielle Java 8
FROM openjdk:8-jdk-bullseye

# Exposer le port de l’application Spring Boot
EXPOSE 8080

# Copier le fichier JAR généré dans le conteneur
COPY target/gestion-station-skii-0.0.2-SNAPSHOT.jar app.jar

# Démarrer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
