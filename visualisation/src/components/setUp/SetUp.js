import React, { useState, useEffect } from 'react';
import CustomForm from './CustomForm';
import CustomFormSkeleton from './CustomFormSkeleton';

const SetUp = () => {
	const [isLoading, setIsLoading] = useState(true);

	const mockJson = {
		width: 100,
		height: 100,
		startEnergy: 200,
		moveEnergy: 1,
		plantEnergy: 40,
		jungleRatio: 0.2,
	};
	return isLoading ? <CustomFormSkeleton /> : <CustomForm jsonData={mockJson} />;
};

export default SetUp;
