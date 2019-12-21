import React, { useState, useEffect } from 'react';
import { notification, Result, Button, message } from 'antd';
import axios from 'axios';

const SuccessScreen = ({ onClick }) => (
	<Result
		status="success"
		title="Simulation finished successfully!"
		subTitle="Too see the visualisation click the button below."
		extra={[
			<Button type="primary" key="console" onClick={onClick}>
				Go to visualisation
			</Button>,
		]}
	/>
);

const Simulation = (props) => {
	const { goToVisualisation, setSimulationRunning } = props;
	const [isLoading, setIsLoading] = useState(true);

	useEffect(() => {
		notification.info({
			message: ':((',
			description: 'I tried to run this java app from Node JS (using terminal) but after numerous hours I failed.',
			duration: 10,
		});
		setSimulationRunning(true);

		const interval = setInterval(async () => {
			const res = await axios.get('/isSimulationFinished');
			if (res.data.finished) {
				setSimulationRunning(false);
				setIsLoading(false);
				message.success('Simulation finished!');
				clearInterval(interval);
			}
		}, 3000);
		return () => interval && clearInterval(interval);
	}, []);

	return isLoading ? (
		<Result title="Go to the javaSimulator directory and run Simulator.java main function" />
	) : (
		<SuccessScreen onClick={goToVisualisation} />
	);
};

export default Simulation;
