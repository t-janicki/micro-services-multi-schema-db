FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8300
ARG JAR_FILE=target/credit-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} credit-service.jar
ENTRYPOINT ["java", "-jar", "credit-service.jar"]