import React from 'react';
import { DynamicBarChart } from 'react-dynamic-charts';

const CustomDynamicBarChart = (props) => {
	return <DynamicBarChart data={props.data} iterationTimeout={props.interval} startAutomatically={props.running} />;
};

export default CustomDynamicBarChart;
