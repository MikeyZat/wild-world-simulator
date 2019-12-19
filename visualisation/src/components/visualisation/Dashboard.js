import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { message, InputNumber } from 'antd';
import { DynamicBarChart } from 'react-dynamic-charts';
import { visualisationShaper } from '../../util/visualisationShaper';
import Loader from '../Loader';
import SliderInput from './SliderInput';

import 'react-dynamic-charts/dist/index.css';

const Dashboard = () => {
	const [isFetching, setIsFetching] = useState(true);
	const [time, setTime] = useState(200);
	const [genesData, setGenesData] = useState();
	const [countData, setCountData] = useState();
	const [averageData, setAverageData] = useState();
	const [isSimulationRunning, setIsSimulationRunning] = useState(false);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const res = await axios.get('/simulation');
				const { data } = res;
				const { genesData: newGenesData, countData: newCountData, tableData } = visualisationShaper(data);
				console.log(data);
				setGenesData(newGenesData);
				setCountData(newCountData);
				setAverageData(tableData)
			} catch (e) {
				message.error('Failed to fetch simulation data');
			} finally {
				setIsFetching(false);
			}
		};
		fetchData();
	}, []);

	console.log(genesData);
	console.log(countData);
	console.log(averageData);

	return isFetching ? <Loader height={400} /> : (
	<React.Fragment>
		<SliderInput onChange={setTime} value={time} isSimulationRunning={isSimulationRunning} />
		<DynamicBarChart
			data={genesData}
		/>
	</React.Fragment>
	);
};

export default Dashboard;
