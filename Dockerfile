# Stage 1: Build the app using Maven
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app

# Copy Maven config and source files
COPY pom.xml .
COPY src ./src

# Build the jar (skip tests to speed up)
RUN mvn clean package -DskipTests

# Stage 2: Run the app using a lightweight Java runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
