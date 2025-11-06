# Utiliser une image légère et maintenue de Java 8
FROM eclipse-temurin:8-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le jar généré par Maven (modifie le nom si nécessaire)
COPY target/*.jar app.jar

# Exposer le port 8080 (Spring Boot)
EXPOSE 8080

# Lancer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
