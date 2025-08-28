FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 2006

ENTRYPOINT ["java","-jar","app.jar"]
