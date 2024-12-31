# Stage 1
# Start with a base image containing Java runtime
FROM openjdk:17-slim as build-1

# Add maintainer info
LABEL maintainer="Nareash <nareash20010150@gmail.com>"

# The applications jar file
ARG JAR_FILE

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Unpackage jar files
RUN mkdir -p "target/dependency" && (cd target/dependency; jar -xf /app.jar)

# Stage 2
# Same java runtime
FROM openjdk:17-slim as build-2

# Add volume pointing to /tmp
VOLUME /tmp

# Copy unpacked application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build-1 ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build-1 ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build-1 ${DEPENDENCY}/BOOT-INF/classes /app

# Execute the application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.optimagrowth.configserver.ConfigurationServerApplication"]
