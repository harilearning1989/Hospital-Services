FROM openjdk:20
MAINTAINER Hari Duddukunta <hari.learning1989@gmail.com>
ARG WAR_FILE=/target/login-service.jar

WORKDIR /opt/app
COPY ${WAR_FILE}  /app/
EXPOSE 8082
CMD ["java", "-Xmx200m", "-jar", "/app/login-service.jar"]


#docker build -t login-service:1.0.0 .
#docker run -p 8082:8082 --name login-service --link eureka-server login-service:1.0.0