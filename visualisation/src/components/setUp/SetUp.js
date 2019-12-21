import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { message } from 'antd';
import CustomForm from './CustomForm';
import CustomFormSkeleton from './CustomFormSkeleton';

const SetUp = (props) => {
	const { goToSimulation } = props;
	const [isLoading, setIsLoading] = useState(true);
	const [jsonData, setJsonData] = useState({});

	useEffect(() => {
		const fetchData = async () => {
			try {
				const res = await axios.get('/parameters');
				setJsonData(res.data);
			} catch (e) {
				message.error('Failed to fetch initial data from parameters.json');
			} finally {
				setIsLoading(false);
			}
		};
		fetchData();
	}, []);

	const handleSubmit = async (data) => {
		try {
			await axios.post('/runSimulation', data);
			goToSimulation();
		} catch (e) {}
	};

	return isLoading ? <CustomFormSkeleton /> : <CustomForm jsonData={jsonData} onSubmit={handleSubmit} />;
};

export default SetUp;
