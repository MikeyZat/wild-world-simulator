import React, { useState, useEffect } from 'react';
import { Row, Col, Card, Statistic } from 'antd';

const CustomDataTable = (props) => {
	const [currentIndex, setCurrentIndex] = useState(0);
	const { data, interval, running } = props;

	useEffect(() => {
		let i = 0;
		let newInterval;
		if (running) {
			newInterval = setInterval(() => {
				i++;
				setCurrentIndex(i);
			}, interval);
		}
		return () => clearInterval(newInterval);
	}, [running]);

	const currentItems = data[currentIndex];
	return (
		<React.Fragment>
			{currentItems.map((item) => (
				<Row span={1} key={item.title} type="flex" justify="center" align="middle">
					<Col span={16}>
						<Card>
							<Statistic title={item.title} value={item.value} precision={item.precision} formatter={item.formatter} />
						</Card>
					</Col>
				</Row>
			))}
		</React.Fragment>
	);
};

export default CustomDataTable;
