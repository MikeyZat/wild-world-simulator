import React from 'react';
import { Icon } from 'antd';

const colors = ['red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet', 'brown'];

const icons = [
	<Icon type="arrow-up" />,
	<Icon type="arrow-up" style={{ transform: 'rotate(45deg)' }} />,
	<Icon type="arrow-right" />,
	<Icon type="arrow-right" style={{ transform: 'rotate(45deg)' }} />,
	<Icon type="arrow-down" />,
	<Icon type="arrow-down" style={{ transform: 'rotate(45deg)' }} />,
	<Icon type="arrow-left" />,
	<Icon type="arrow-left" style={{ transform: 'rotate(45deg)' }} />,
];

export const visualisationShaper = (data) => {
	const genesData = [];
	const tableData = [];

	for (let i = 0; i < data.length; i++) {
		const current = data[i];
		genesData.push({
			values: current.genesFrequency.map((value, index) => ({
				id: index,
				label: (
					<React.Fragment>
						{`Gene ${index}`}
						{icons[index]}
					</React.Fragment>
				),
				value,
				color: colors[index],
			})),
		});

		tableData.push([
			{
				title: 'animals',
				value: current.animalCount,
				precision: 0,
			},
			{
				title: 'grass',
				value: current.grassCount,
				precision: 0,
			},
			{
				title: 'average energy',
				value: current.averageEnergy,
				precision: 2,
			},
			{
				title: 'average children number',
				value: current.averageChildrenNum,
				precision: 2,
			},
			{
				title: 'average life length',
				value: current.averageLifeLength,
				precision: 2,
			},
		]);
	}
	return { genesData, tableData };
};
