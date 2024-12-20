FROM maven:3.9.4-bellsoft-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src/ /app/src

RUN mvn clean package -DskipTests

FROM bellsoft/liberica-runtime-alpine:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 3000

