# --------- Build stage ---------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# copy pom + wrapper trước để cache dependency
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline -B

# copy source và build
COPY src src
RUN ./mvnw package -DskipTests

# --------- Runtime stage ---------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
