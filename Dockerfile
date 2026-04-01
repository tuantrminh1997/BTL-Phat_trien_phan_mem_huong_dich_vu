FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY gradlew .
COPY gradle ./gradle
RUN chmod +x gradlew

# Copy file cấu hình Gradle
COPY build.gradle.kts settings.gradle.kts ./

RUN ./gradlew dependencies --no-daemon || true

COPY src ./src

RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

RUN addgroup -S soccerapp && adduser -S soccerapp -G soccerapp

COPY --from=builder /app/build/libs/SoccerRest-*.jar app.jar

RUN chown soccerapp:soccerapp app.jar

USER soccerapp

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

