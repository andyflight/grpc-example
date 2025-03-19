# Build stage
FROM gradle:jdk21-ubi-minimal AS build
WORKDIR /home/app

# dependency downloading
COPY ./user-service/build.gradle /home/app/build.gradle
COPY ./user-service/settings.gradle /home/app/settings.gradle
COPY ./user-service/src/main/java/com/example/user_service/UserServiceApplication.java /home/app/src/main/java/com/example/user_service/UserServiceApplication.java
COPY ./shared/ /home/shared
RUN gradle -b /home/app/build.gradle clean build

# build
COPY ./user-service/ /home/app
RUN gradle -b /home/app/build.gradle clean build

# Run stage
FROM azul/zulu-openjdk-alpine:21-jre-latest
EXPOSE 9090
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
