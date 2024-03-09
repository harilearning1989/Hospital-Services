FROM openjdk:20
MAINTAINER Hari Duddukunta <hari.learning1989@gmail.com>
ARG WAR_FILE=/build/libs/gateway-server.jar

WORKDIR /opt/app
COPY ${WAR_FILE}  /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-server.jar"]
EXPOSE 8081

#docker build -t gateway-server:1.0.0 .
#docker run -p 8081:8081 --name gateway-server --link eureka-server gateway-server:1.0.0
#docker run -p 8081:8081 --link sad_goodall gateway-server:1.0.0
#docker run -p 8081:8081 -t gateway-server:1.0.0
#docker run -d -p 8081:8081 -t spring-mvc-web-h2:1.0.0
#vdocker build -t spring-web-h2:1 .