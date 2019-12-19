import React, { useState, useEffect } from 'react';
import CustomForm from './CustomForm';
import CustomFormSkeleton from './CustomFormSkeleton';

const SetUp = (props) => {
	const { goToSimulation } = props;
	const [isLoading, setIsLoading] = useState(true);

	const mockJson = {
		width: 100,
		height: 100,
		startEnergy: 200,
		moveEnergy: 1,
		plantEnergy: 40,
		jungleRatio: 0.2,
	};
	setTimeout(() => setIsLoading(false), 1000);

	const handleSubmit = (data) => goToSimulation();

	return isLoading ? <CustomFormSkeleton /> : <CustomForm jsonData={mockJson} onSubmit={handleSubmit} />;
};

export default SetUp;
