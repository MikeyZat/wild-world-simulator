const fs = require('fs');
const express = require('express');
const { exec } = require('child_process');

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.get('/parameters', (req, res) => {
	const rawUsersData = fs.readFileSync('./parameters.json');
	res.send(JSON.parse(rawUsersData)).status(200);
});

app.post('/runSimulation', (req, res) => {
	const { body } = req;
	console.log(body);
	fs.writeFileSync('./parameters.json', JSON.stringify(body));
	try {
		fs.unlinkSync('./results.json');
	} catch (e) {
		console.log(e);
	}
	res.send(req.body).status(200);
});

app.get('/isSimulationFinished', (req, res) => {
	const finished = fs.existsSync('./results.json');
	res.send({ finished }).status(200);
});

app.get('/simulation', (req, res) => {
	const rawUsersData = fs.readFileSync('./results.json');
	res.send(JSON.parse(rawUsersData)).status(200);
});

const port = 5000;

app.listen(port, () => `Server running on port ${port}`);
