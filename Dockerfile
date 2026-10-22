# Stage 1: Build the application (The Factory)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Copy all source code and the Maven wrapper
COPY . .
# Grant execution permissions to the wrapper and compile the .jar
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application (The Server)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy the .jar from the "builder" stage above
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]