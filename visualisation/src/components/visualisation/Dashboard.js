import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Result, Row, Col, Button } from 'antd';
import CustomDynamicBarChart from './CustomDynamicBarChart';
import CustomDataTable from './CustomDataTable';
import Map from './Map';
import { visualisationShaper } from '../../util/visualisationShaper';
import Loader from '../Loader';
import SliderInput from './SliderInput';

import 'react-dynamic-charts/dist/index.css';

const Dashboard = () => {
	const [isFetching, setIsFetching] = useState(true);
	const [error, setError] = useState();
	const [time, setTime] = useState(200);
	const [genesData, setGenesData] = useState();
	const [tableData, setTableData] = useState();
	const [mapData, setMapData] = useState();
	const [isSimulationRunning, setIsSimulationRunning] = useState(false);
	const [currIndex, setCurrIndex] = useState(0);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const res = await axios.get('/simulation');
				const { data } = res;
				const { genesData: newGenesData, tableData: newTableData, mapData: newMapData } = visualisationShaper(data);
				console.log(data);
				setGenesData(newGenesData);
				setTableData(newTableData);
				setMapData(newMapData);
			} catch (e) {
				setError('Failed to fetch simulation data');
			} finally {
				setIsFetching(false);
			}
		};
		fetchData();
	}, []);

	const onSimulationStop = (offset) => setCurrIndex(currIndex + offset);

	return isFetching ? (
		<Loader height={400} />
	) : error ? (
		<Result status="500" title="500" subTitle={error} />
	) : (
		<div>
			<Row>
				<Col span={24} style={{ textAlign: 'center' }}>
					<SliderInput onChange={setTime} value={time} isSimulationRunning={isSimulationRunning} />
				</Col>
			</Row>
			<Row style={{ marginBottom: 30 }}>
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
				<Col span={4}>
					<CustomDataTable
						key={`custom-table-${currIndex + isSimulationRunning}`}
						data={tableData.slice(currIndex)}
						interval={time}
						running={isSimulationRunning}
					/>
				</Col>
				<Col span={20}>
					<Map
						key={`map-${currIndex + isSimulationRunning}`}
						data={mapData.slice(currIndex)}
						interval={time}
						running={isSimulationRunning}
						onStop={onSimulationStop}
					/>
				</Col>
			</Row>
			<Row style={{ marginTop: 40 }}>
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
