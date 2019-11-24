FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 1001
ADD eureka-server-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]