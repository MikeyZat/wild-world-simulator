import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Dashboard = () => {
	const [time, setTime] = useState();

	useEffect(() => {
		const fetchData = async () => {
			const res = await axios.fetchData('/getSimulation');
			const { data } = res;
			console.log(data);
		};
	}, []);

	return <div>Hello world</div>;
};

export default Dashboard;
