FROM openjdk:17
ADD target/springboot-mongo-docker.jar app.jar
ENTRyPOINT ["java","-jar","app.jar"]
