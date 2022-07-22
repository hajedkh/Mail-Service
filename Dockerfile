FROM openjdk:11-oracle

ADD target/mail-service-0.0.1-SNAPSHOT.jar app.jar


CMD ["java", "-jar", "/app.jar"]