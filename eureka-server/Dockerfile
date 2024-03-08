FROM openjdk:20
MAINTAINER Hari Duddukunta <hari.learning1989@gmail.com>
ARG WAR_FILE=/target/eureka-server.jar

WORKDIR /opt/app
COPY ${WAR_FILE}  /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server.jar"]
EXPOSE 8761

#docker build -t eureka-server:1.0.0 .
#docker run -p 8761:8761 --name eureka-server eureka-server:1.0.0
#docker run -d -p 8761:8761 --name eureka-server eureka-server
#docker run -p 8761:8761 -t eureka-server:1.0.0
#docker run -d -p 8081:8081 -t spring-mvc-web-h2:1.0.0
#vdocker build -t spring-web-h2:1 .