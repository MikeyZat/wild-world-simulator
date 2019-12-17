import React from 'react';
import { Switch, Route } from 'react-router-dom';
import WelcomePage from './components/welcomePage/WelcomePage';
import Dashboard from './components/dashboard/Dashboard';

const Routes = ({ location }) => (
	<Switch>
		<Route location={location} exact path="/" component={WelcomePage} />
		<Route location={location} exact path="/simulation" component={Dashboard} />
	</Switch>
);

export default Routes;
