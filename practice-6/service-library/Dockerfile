FROM openjdk:19-alpine as build
WORKDIR service-library

COPY . .

RUN apk add gradle
RUN gradle wrapper
RUN ./gradlew build


FROM openjdk:19-alpine
WORKDIR service-library

COPY --from=build /service-library/build/libs/*.jar /service-library/app.jar
EXPOSE 8080

CMD ["java", "-jar", "/service-library/app.jar"]