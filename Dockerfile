FROM openjdk:11.0.12-jre-buster

WORKDIR /app

COPY target/reactive.mongo*.jar ./application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]

