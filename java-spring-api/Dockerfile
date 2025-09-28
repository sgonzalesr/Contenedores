# ===== Build stage (con Maven) =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests clean package

# ===== Runtime =====
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN apt-get update && apt-get install -y --no-install-recommends curl \
  && rm -rf /var/lib/apt/lists/*
RUN useradd -ms /bin/bash appuser
USER appuser

# Copia el JAR ejecutable 
COPY --from=build /src/target/java-spring-api-1.0.0.jar /app/app.jar
#apúntalo al nombre exacto de artifact/version

ENV SERVER_PORT=8081 APP_MESSAGE="Hello from Java Spring!" JAVA_OPTS=""
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -fsS http://localhost:8081/health || exit 1
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
