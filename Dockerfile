# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package

# Package stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/mortgagecalculator-0.0.1-SNAPSHOT.jar /app/app.jar
COPY src/main/resources/prospects.txt /app/
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
