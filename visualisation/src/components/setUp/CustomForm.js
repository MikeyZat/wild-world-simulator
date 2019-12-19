import React, { useState } from 'react';
import { Form, InputNumber, Row, Col, Button } from 'antd';

const CustomForm = (props) => {
	const { jsonData } = props;

	const [width, setWidth] = useState(jsonData.width);
	const [height, setHeight] = useState(jsonData.height);
	const [startEnergy, setStartEnergy] = useState(jsonData.startEnergy);
	const [moveEnergy, setMoveEnergy] = useState(jsonData.moveEnergy);
	const [plantEnergy, setPlantEnergy] = useState(jsonData.plantEnergy);
	const [jungleRatio, setJungleRatio] = useState(jsonData.jungleRatio);

	const handleSubmit = (e) => {
		e.preventDefault();
	};

	const formItemLayout = {
		labelCol: {
			xs: { span: 24 },
			sm: { span: 8 },
		},
		wrapperCol: {
			xs: { span: 24 },
			sm: { span: 16 },
		},
	};

	return (
		<Form {...formItemLayout} onSubmit={handleSubmit}>
			<Row gutter={24}>
				<Col span={8}>
					<Form.Item label="Width" required>
						<InputNumber min={10} max={200} value={width} onChange={setWidth} />
					</Form.Item>
					<Form.Item label="Height" required>
						<InputNumber min={10} max={200} value={height} onChange={setHeight} />
					</Form.Item>
				</Col>
				<Col span={8}>
					<Form.Item label="Start energy" required>
						<InputNumber min={1} max={1000} value={startEnergy} onChange={setStartEnergy} />
					</Form.Item>
					<Form.Item label="Move energy" required>
						<InputNumber min={1} max={100} value={moveEnergy} onChange={setMoveEnergy} />
					</Form.Item>
				</Col>
				<Col span={8}>
					<Form.Item label="Plant energy" required>
						<InputNumber min={10} max={100} value={plantEnergy} onChange={setPlantEnergy} />
					</Form.Item>
					<Form.Item label="Jungle ratio" required>
						<InputNumber min={0.01} max={0.99} step={0.1} value={jungleRatio} onChange={setJungleRatio} />
					</Form.Item>
				</Col>
			</Row>
			<Row>
				<Col span={24} style={{ textAlign: 'center' }}>
					<Form.Item
						wrapperCol={{
							xs: { span: 24 },
							sm: { span: 24 },
						}}
					>
						<Button type="primary" htmlType="submit">
							Run simulation
						</Button>
					</Form.Item>
				</Col>
			</Row>
		</Form>
	);
};

export default CustomForm;
