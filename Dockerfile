FROM openjdk:17-oracle

EXPOSE 8082

ADD target/simplewebapp-0.0.1-SNAPSHOT.jar simplewebapp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","simplewebapp-0.0.1-SNAPSHOT.jar"]
