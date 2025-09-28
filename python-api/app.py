import os
from flask import Flask

app = Flask(__name__)

PORT = int(os.getenv("PORT", "8083"))
MESSAGE = os.getenv("APP_MESSAGE", "Hello from Python Flask!")

@app.get("/")
def index():
    return MESSAGE

@app.get("/health")
def health():
    return "OK"

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=PORT)
