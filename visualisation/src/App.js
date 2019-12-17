import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import Routes from './Routes';
import './globalStyles.css';

const App = () => (
	<div className="app-container">
		<BrowserRouter>
			<Routes />
		</BrowserRouter>
	</div>
);

export default App;
