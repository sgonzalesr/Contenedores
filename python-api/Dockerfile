FROM python:3.12-alpine
WORKDIR /app

# Usuario no root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Dependencias
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Código
COPY app.py .

# curl para HEALTHCHECK
RUN apk add --no-cache curl

# Variables por defecto (puedes sobrescribirlas en docker run)
ENV PORT=8083 \
    APP_MESSAGE="Hello from Python Flask!"

EXPOSE 8083

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -fsS http://localhost:8083/health || exit 1

USER appuser

CMD ["python","app.py"]
