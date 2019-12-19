import React, { useState } from 'react';
import { Layout, Typography, Breadcrumb, Steps, PageHeader, Tooltip, Icon } from 'antd';
import WelcomeModal from './components/welcomeModal/WelcomeModal';
import SetUp from './components/setUp/SetUp';
import './globalStyles.css';

const { Header, Content, Footer } = Layout;
const { Step } = Steps;
const { Title, Text } = Typography;

export const SETUP = 0;
export const LOADING = 1;
export const VISUALISATION = 2;

const tabsDetails = [
	{
		breadcrumb: 'Setup',
		progressTitle: 'Set up',
		title: 'Provide starting data for simulation',
		infoTooltip: (
			<Tooltip
				title={`Starting data are loaded from parameters.json.
			Your changes will be also saved to that file as it is the simulation main config file.`}
			>
				<Icon type="question-circle" style={{ cursor: 'pointer' }} />
			</Tooltip>
		),
	},
	{
		breadcrumb: 'Loading',
		progressTitle: 'Simulate',
		activeProgressIcon: <Icon type="loading" />,
		title: 'Wait untill simulation is finished',
		infoTooltip: (
			<Tooltip title="Simulation is running. It should last a few seconds. Do not refresh this page!">
				<Icon type="question-circle" />
			</Tooltip>
		),
	},
	{
		breadcrumb: 'Visualisation',
		progressTitle: 'Visualize',
		title: 'Enjoy visualisation',
		infoTooltip: (
			<Tooltip title="Run visualisation by hitting the 'Run visualisation' button.">
				<Icon type="question-circle" />
			</Tooltip>
		),
	},
];

const App = () => {
	const [currentTab, setCurrentTab] = useState(SETUP);
	const [showModal, setShowModal] = useState(true);

	const currentTabDetails = tabsDetails[currentTab];

	return (
		<Layout className="layout" style={{ minHeight: '100vh' }}>
			<Header>
				<Title style={{ margin: '10px 0 0 0', color: '#fff' }}>
					<Text style={{ color: '#4890ff' }}>W</Text>ild
					<Text style={{ color: '#52c41a' }}> W</Text>orld
					<Text style={{ color: '#4890ff' }}> S</Text>imulator
				</Title>
			</Header>
			<Content style={{ padding: '0 50px' }}>
				<Breadcrumb style={{ margin: '16px 0' }}>
					<Breadcrumb.Item>{currentTabDetails.breadcrumb}</Breadcrumb.Item>
				</Breadcrumb>
				<div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
					<Steps current={currentTab}>
						{tabsDetails.map((tab, index) => (
							<Step
								key={tab.progressTitle}
								title={tab.progressTitle}
								icon={currentTab === index && tab.activeProgressIcon ? tab.activeProgressIcon : ''}
							/>
						))}
					</Steps>
					<PageHeader
						style={{
							border: '1px solid rgb(235, 237, 240)',
							margin: '20px 0',
						}}
						title={currentTabDetails.title}
						subTitle={currentTabDetails.infoTooltip}
					/>
					{currentTab === SETUP && <SetUp />}
				</div>
				<WelcomeModal isModalVisible={showModal} closeModal={() => setShowModal(false)} />
			</Content>
			<Footer style={{ textAlign: 'center' }}>Mikołaj Zatorski ©2019/2020</Footer>
		</Layout>
	);
};

export default App;
