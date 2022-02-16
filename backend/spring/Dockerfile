FROM gradle:7.3.3-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17-alpine AS runtime

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/spring-0.0.1-SNAPSHOT.jar /app/application.jar

ENTRYPOINT ["java","-jar","/app/application.jar"]
