FROM openjdk:11
ARG JAR_FILE=/build/libs/Fikirtepe-Student-Information-System-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ADD src/main/resources/config.properties src/main/resources/config.properties
ENTRYPOINT ["java", "-jar" ,"/app.jar"]