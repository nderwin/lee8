FROM openjdk:8-jdk-slim AS build-env
WORKDIR /app
COPY target/lee8-thorntail.jar lee8-thorntail.jar
COPY target/project-defaults.yml project-defaults.yml
COPY target/datasource-defaults.yml datasource-defaults.yml

FROM gcr.io/distroless/java
COPY --from=build-env /app /app
WORKDIR /app
CMD ["lee8-thorntail.jar", "-Djava.net.preferIPv4Stack=true", "-s", "project-defaults.yml", "-s", "datasource-defaults.yml"]
