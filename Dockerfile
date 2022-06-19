FROM adoptopenjdk:11-jre-hotspot as builder
EXPOSE 8080
ADD target/demo-app .jar demo-app.jar
ENTRYPOINT{"java" , "-jar" , "/demo-app.jar"}