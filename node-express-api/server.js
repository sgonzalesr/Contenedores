const express = require('express');
const app = express();

const PORT = process.env.PORT || 8082;
const MESSAGE = process.env.APP_MESSAGE || 'Hello from Node Express!';

app.get('/', (_, res) => res.send(MESSAGE));
app.get('/health', (_, res) => res.send('OK'));

app.listen(PORT, () => console.log(`Node API listening on ${PORT}`));
