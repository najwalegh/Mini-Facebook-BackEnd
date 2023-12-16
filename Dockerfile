FROM openjdk:17
ADD target/*.jar app.jar
ENTRyPOINT ["java","-jar","app.jar"]
