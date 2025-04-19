# -------- Build Stage --------
FROM maven:3.8.5-openjdk-17 AS build

# Copy source code
COPY . .

# Package the application (skip tests for faster build)
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# Copy only the packaged jar from the build stage
COPY --from=build /target/TverLeng-0.0.1-SNAPSHOT.jar TverLeng.jar

# Expose the app port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "TverLeng.jar"]
