FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8200
ARG JAR_FILE=target/customer-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} customer-service.jar
ENTRYPOINT ["java", "-jar", "customer-service.jar"]