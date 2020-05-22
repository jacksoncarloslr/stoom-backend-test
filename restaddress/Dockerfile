FROM openjdk:8-alpine

MAINTAINER Jackson Ramalho "jacksoncarloslr@gmail.com"

EXPOSE 8081

WORKDIR /usr/local/bin/

COPY ./target/restaddress-0.0.1-SNAPSHOT.jar restaddress.jar

CMD ["java", "-jar", "restaddress.jar"]