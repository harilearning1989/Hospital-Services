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
#docker run --name gateway-server --network eureka-network -p 8081:8081 -d gateway-server:1.0.0
#docker run --name login-service --network eureka-network -p 8082:8082 -d login-service:1.0.0

#MySql Commans

#docker run --name mysql -v /Users/hariduddukunta/MyWork/Softwares/MySqlData/:/var/lib/mysql --network eureka-network -e MYSQL_ROOT_PASSWORD=dudkrish1A -d -p 3306:3306 mysql:8
#docker run --name mysql -v /Users/hariduddukunta/MyWork/Softwares/MySqlData/:/var/lib/mysql --network eureka-network -e MYSQL_ROOT_PASSWORD=dudkrish1A -e MYSQL_DATABASE=hosp_mgmt -e MYSQL_USER=hari -e MYSQL_PASSWORD=dudkrish1A -d -p 3306:3306 mysql:8