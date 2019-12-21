import React, { useState, useEffect } from 'react';
import { notification, Result, Button, message } from 'antd';
import axios from 'axios';
import Loader from '../Loader';


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
		notification.warning({
			message: 'Simulation is running',
			description: 'Do not refresh this page!',
			duration: 6,
		});
		setSimulationRunning(true);

		const interval = setInterval(async () => {
			const res = await axios.get('/isSimulationFinished');
			if (res.data.finished) {
				setSimulationRunning(false);
				setIsLoading(false);
				message.success('Simulation finished!')
			}
		}, 3000);
		return () => clearInterval(interval);
	}, []);

	return isLoading ? <Loader height={300} /> : <SuccessScreen onClick={goToVisualisation} />;
};

export default Simulation;
