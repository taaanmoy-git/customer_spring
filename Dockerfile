# Stage 1: Build the application (optional, if you build inside Docker)
# FROM maven:3.8.5-openjdk-17 AS builder
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

# Stage 2: Run the application Jar Version
FROM openjdk:21-jdk-slim

# Expose the application's port (update if not 8080)
EXPOSE 8080

# Copy jar from host (replace 'target/*.jar' with your actual jar path)
COPY target/customer_spring.jar customer_spring.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "customer_spring.jar"]
