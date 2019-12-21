import React from 'react';
import { Spin, Row, Col } from 'antd';

const Loader = (props) => (
	<Row type="flex" justify="center" align="middle" style={{ height: props.height }}>
		<Col span={4} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
			<Spin size="large" />
		</Col>
	</Row>
);

export default Loader;
