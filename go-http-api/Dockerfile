# ===== Build stage =====
FROM golang:1.22-alpine AS build
WORKDIR /src

# Habilitar módulos y bajar dependencias (aunque no tengamos externas)
COPY go.mod .
RUN go mod download

# Copiar código y compilar binario estático
COPY . .
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o app

# ===== Runtime stage =====
FROM alpine:3.20
WORKDIR /app

# Usuario no root + curl para HEALTHCHECK
RUN addgroup -S appgroup && adduser -S appuser -G appgroup \
  && apk add --no-cache curl

USER appuser

# Copiar binario
COPY --from=build /src/app /app/app

# Variables por defecto (se pueden sobreescribir al ejecutar)
ENV PORT=8084 \
    APP_MESSAGE="Hello from Go HTTP!"

EXPOSE 8084

# Healthcheck al endpoint /health
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -fsS http://localhost:8084/health || exit 1

ENTRYPOINT ["/app/app"]
