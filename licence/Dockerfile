#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-slim as build
# Add Maintainer Info
LABEL maintainer="Nareash <nareash20010150@gmail.com>"
# The application's jar file
WORKDIR application
ARG JAR_FILE=target/*.jar
# Add the application's jar to the container
COPY ${JAR_FILE} app.jar
#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../../app.jar)

#stage 2
#Same Java runtime
FROM openjdk:17-slim
#Add volume pointing to /tmp
VOLUME /tmp
#Copy unpackaged application to new container
ARG DEPENDENCY=/application/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.license.LicenseApplication"]