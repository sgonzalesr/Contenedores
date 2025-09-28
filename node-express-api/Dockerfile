# Imagen base ligera
FROM node:20-alpine

# Directorio de trabajo
WORKDIR /app

# Crear usuario/grupo no root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copiar manifiestos primero para aprovechar la cache de Docker
COPY package*.json ./

# Instalar dependencias de producción de forma reproducible
RUN npm ci --only=production

# Copiar el código
COPY server.js .

# curl para el HEALTHCHECK
RUN apk add --no-cache curl

# Variables por defecto (puedes sobreescribir en docker run)
ENV PORT=8082 \
    APP_MESSAGE="Hello from Node Express!"

# Documentar puerto
EXPOSE 8082

# Healthcheck al endpoint /health
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -fsS http://localhost:8082/health || exit 1

# Seguridad: ejecutar como usuario no root
USER appuser

# Comando de arranque
CMD ["npm", "start"]
