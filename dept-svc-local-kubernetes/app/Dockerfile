FROM openjdk:8-jdk-slim
EXPOSE 8080
WORKDIR /department-service/app
COPY target/dept-svc-0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]