import React, { useState, useEffect } from 'react';
import { Row, Col, Card, Statistic, Icon } from 'antd';

const CustomDataTable = (props) => {
	const [currentIndex, setCurrentIndex] = useState(0);
	const { data, interval, running } = props;

	const incrementState = () => setCurrentIndex(currentIndex + 1);

	useEffect(() => {
		let newInterval;
		if (running) {
			newInterval = setInterval(() => incrementState(), interval);
		}
		return () => clearInterval(newInterval);
	}, [running]);

	const currentItems = data[currentIndex];
	return (
		<React.Fragment>
			{currentItems.map((item) => (
				<Row span={2}>
					<Col span={6} key={item.title}>
						<Card>
							<Statistic title={item.title} value={item.value} precision={item.precision} />
						</Card>
					</Col>
				</Row>
			))}
		</React.Fragment>
	);
};

export default CustomDataTable;
