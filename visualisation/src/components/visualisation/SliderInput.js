import React from 'react';
import { Slider, Col, Row, InputNumber, Typography } from 'antd';

const { Title } = Typography;

const SliderInput = (props) => {
	const onSliderChange = (value) => props.onChange(value);
	return (
		<div>
			<Row style={{ display: 'flex', justifyContent: 'center' }}>
				<Col span={12}>
					<Title level={4}>Choose frame frequency (in miliseconds) </Title>
				</Col>
			</Row>
			<Row style={{ display: 'flex', justifyContent: 'center' }}>
				<Col span={8}>
					<Slider
						min={100}
						max={1000}
						onChange={onSliderChange}
						value={typeof props.value === 'number' ? props.value : 0}
						disabled={props.isSimulationRunning}
					/>
				</Col>
				<Col span={4}>
					<InputNumber
						min={100}
						max={1000}
						step={10}
						style={{ marginLeft: 16 }}
						value={props.value}
						onChange={onSliderChange}
						disabled={props.isSimulationRunning}
					/>
				</Col>
			</Row>
		</div>
	);
};

export default SliderInput;
