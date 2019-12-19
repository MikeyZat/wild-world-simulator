import React from 'react';
import { Form, Skeleton, Row, Col } from 'antd';

const CustomFormSkeleton = () => {
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
		<Form {...formItemLayout}>
			<Row gutter={24}>
				<Col span={8}>
					<Form.Item label="Width" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
					<Form.Item label="Height" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
				</Col>
				<Col span={8}>
					<Form.Item label="Start energy" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
					<Form.Item label="Move energy" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
				</Col>
				<Col span={8}>
					<Form.Item label="Plant energy" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
					<Form.Item label="Jungle ratio" required>
						<Skeleton paragraph={false} active />
					</Form.Item>
				</Col>
			</Row>
			<Row>
				<Col offset={10} span={4} style={{ textAlign: 'center' }}>
					<Form.Item>
						<Skeleton paragraph={false} active />
					</Form.Item>
				</Col>
			</Row>
		</Form>
	);
};

export default CustomFormSkeleton;
