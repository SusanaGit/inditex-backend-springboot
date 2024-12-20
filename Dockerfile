FROM maven:3.9.4-bellsoft-21 AS build

WORKDIR /app

COPY pom.xml .