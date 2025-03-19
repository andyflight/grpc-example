# Build stage
FROM gradle:jdk21-ubi-minimal AS build
WORKDIR /home/app

# dependency downloading
COPY ./message-service/build.gradle /home/app/build.gradle
COPY ./message-service/settings.gradle /home/app/settings.gradle
COPY ./message-service/src/main/java/com/example/message_service/MessageServiceApplication.java /home/app/src/main/java/com/example/message_service/MessageServiceApplication.java
COPY ./shared/ /home/shared
RUN gradle -b /home/app/build.gradle clean build

# build
COPY ./message-service/ /home/app
RUN gradle -b /home/app/build.gradle clean build

# Run stage
FROM azul/zulu-openjdk-alpine:21-jre-latest
EXPOSE 9092
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
