# Use a base Java image
FROM openjdk:21-jdk-slim

# Add metadata
LABEL maintainer="Your Name"

# Copy the jar file from target to app.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java","-jar","/app.jar"]
