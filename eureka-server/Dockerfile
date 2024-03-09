FROM openjdk:20
MAINTAINER Hari Duddukunta <hari.learning1989@gmail.com>
ARG WAR_FILE=/target/eureka-server.jar

WORKDIR /opt/app
COPY ${WAR_FILE}  /app/
EXPOSE 8761
#CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server.jar"]
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/eureka-server.jar"]

#docker build -t eureka-server:1.0.0 .
#docker network create my-network
#docker run --name eureka-server --network eureka-network -p 8761:8761 -d eureka-server:1.0.0