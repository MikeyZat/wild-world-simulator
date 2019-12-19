import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { message, Row, Col, Button } from 'antd';
import CustomDynamicBarChart from './CustomDynamicBarChart';
import { visualisationShaper } from '../../util/visualisationShaper';
import Loader from '../Loader';
import SliderInput from './SliderInput';

import 'react-dynamic-charts/dist/index.css';
import CustomDataTable from './CustomDataTable';

const Dashboard = () => {
	const [isFetching, setIsFetching] = useState(true);
	const [time, setTime] = useState(200);
	const [genesData, setGenesData] = useState();
	const [tableData, setTableData] = useState();
	const [isSimulationRunning, setIsSimulationRunning] = useState(false);
	const [currIndex, setCurrIndex] = useState(0);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const res = await axios.get('/simulation');
				const { data } = res;
				const { genesData: newGenesData, tableData: newTableData } = visualisationShaper(data);
				console.log(data);
				setGenesData(newGenesData);
				setTableData(newTableData);
			} catch (e) {
				message.error('Failed to fetch simulation data');
			} finally {
				setIsFetching(false);
			}
		};
		fetchData();
	}, []);

	return isFetching ? (
		<Loader height={400} />
	) : (
		<div>
			<Row>
				<Col span={24} style={{ textAlign: 'center' }}>
					<SliderInput onChange={setTime} value={time} isSimulationRunning={isSimulationRunning} />
				</Col>
			</Row>
			<Row>
				<Col span={24} style={{ textAlign: 'center' }}>
					<Button
						type={isSimulationRunning ? 'danger' : 'primary'}
						onClick={() => setIsSimulationRunning(!isSimulationRunning)}
					>
						{isSimulationRunning ? 'Stop simulation' : 'Start simulation!'}
					</Button>
				</Col>
			</Row>
			<Row>
				<Col span={8}>
					<CustomDataTable data={tableData} interval={time} running={isSimulationRunning} />
				</Col>
				<Col span={16}>{/* Map */}</Col>
			</Row>
			<Row>
				<Col span={24}>
					<CustomDynamicBarChart
						key={`dynamic-chart-${currIndex + isSimulationRunning}`}
						data={genesData.slice(currIndex)}
						interval={time}
						running={isSimulationRunning}
					/>
				</Col>
			</Row>
		</div>
	);
};

export default Dashboard;
