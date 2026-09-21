FROM maven:3.9-eclipse-temurin-25 AS builder

LABEL authors="Abdallah Samir"

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

#stage number 2

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

ENV APP_BLOBS_STORAGE_PATH=/app/blobs

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# you should create network and move the db inside the network or use docker compose will create the network automatically and add all services inside it
# to build the image using docker    -> docker build -t lms-app:1.0 .
# to create the volume if not exists -> docker volume create lms-blobs
# to run the service -> docker run -d  --name lms-app -p 8090:8080   --network lms-network -v lms-blobs:/app/blobs lms-app:1.0