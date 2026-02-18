# Start with a base Java image
FROM eclipse-temurin:21-jre-alpine

# Copy the built jar file into the image
COPY target/portfolio-service-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]