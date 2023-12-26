FROM openjdk:17
MAINTAINER Hari Duddukunta <hari.learning1989@gmail.com>

COPY build/libs/gateway-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service.jar"]

EXPOSE 8086