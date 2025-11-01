# -------------------------------
# Étape 1 : Build de l'application avec Maven
# -------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier tout le projet dans le conteneur
COPY . .

# Compiler et packager le projet (sans exécuter les tests)
RUN mvn clean package -DskipTests

# -------------------------------
# Étape 2 : Image finale avec JDK
# -------------------------------
FROM eclipse-temurin:17-jdk

# Créer un répertoire de travail
WORKDIR /app

# Copier le JAR généré depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de ton application Spring Boot
EXPOSE 8089

# Lancer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
