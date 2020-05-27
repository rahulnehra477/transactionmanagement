#FROM gradle:4.7.0-jdk8-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8073

RUN mkdir /app

COPY build/libs/*.jar /app/transaction-application.jar

ENTRYPOINT ["java","-jar","/app/transaction-application.jar"]
