import React from 'react';
import { Modal, Button } from 'antd';

const WelcomeModal = ({ isModalVisible, closeModal }) => (
	<Modal
		title="Welcome to Wild World Simulator"
		visible={isModalVisible}
		onOk={closeModal}
		onCancel={closeModal}
		style={{ top: 40 }}
		footer={[
			<Button key="ok" type="primary" onClick={closeModal}>
				Got it!
			</Button>,
		]}
	>
		Hi! This project was made for OOP classes. It simulates evolution of simplified animals world. You can find more
		about it
		<Button
			htmlType="a"
			type="link"
			href="https://github.com/apohllo/obiektowe-lab/blob/master/lab8/Readme.md"
			target="blank"
		>
			here.
		</Button>
	</Modal>
);

export default WelcomeModal;
