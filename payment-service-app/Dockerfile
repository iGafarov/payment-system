FROM eclipse-temurin:23-jre-alpine

ARG JAR_FILE=app.jar

WORKDIR /app

copy target/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]