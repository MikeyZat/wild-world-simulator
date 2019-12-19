import React, { useState, useEffect } from 'react';
import { Spin, Row, Col, notification, Result, Button } from 'antd';

const Loader = () => (
	<Row type="flex" justify="center" align="center" style={{ height: 300 }}>
		<Col span={4} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
			<Spin size="large" />
		</Col>
	</Row>
);

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

	setTimeout(() => {
		setIsLoading(false);
		setSimulationRunning(false);
	}, 1000);

	useEffect(() => {
		if (isLoading) {
			notification.warning({
				message: 'Simulation is running',
				description: 'Do not refresh this page!',
				duration: 5,
			});
			setSimulationRunning(true);
		}
	}, []);

	return isLoading ? <Loader /> : <SuccessScreen onClick={goToVisualisation} />;
};

export default Simulation;
