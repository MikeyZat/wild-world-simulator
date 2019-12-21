import React, { useRef, useEffect } from 'react';

const colors = [
	'#000000',
	'#ff0000',
	'#ff6666',
	'#ff6600',
	'#ffcc99',
	'#ffff99',
	'#ccffff',
	'#33ccff',
	'#0099ff',
	'#0033cc',
	'#009900',
];

const Map = (props) => {
	const { data, interval, running, onStop } = props;

	const canvasBackground = useRef(null);
	const canvasMain = useRef(null);

	const mapWidth = data[0].length;
	const mapHeight = data[0][0].length;

	const elemSize = 8;

	const width = mapWidth * elemSize;
	const height = mapHeight * elemSize;

	let currIndex = 0;

	//drawing

	const drawFirstScene = (map) => {
		const context = canvasMain.current.getContext('2d');
		for (let i = 0; i < mapWidth; i++) {
			for (let j = 0; j < mapHeight; j++) {
				const value = map[i][j];
				if (value > 0) {
					context.fillStyle = colors[value];
					context.fillRect(i * elemSize, j * elemSize, elemSize, elemSize);
				}
			}
		}
	};

	const drawBackground = () => {
		const context = canvasBackground.current.getContext('2d');
		context.fillStyle = '#000';
		context.fillRect(0, 0, width, height);
	};

	const draw = (prevMap, nextMap) => {
		const context = canvasMain.current.getContext('2d');
		for (let i = 0; i < mapWidth; i++) {
			for (let j = 0; j < mapHeight; j++) {
				const nextValue = nextMap[i][j];
				const prevValue = prevMap[i][j];
				if (nextValue !== prevValue) {
					context.fillStyle = colors[nextValue];
					context.fillRect(i * elemSize, j * elemSize, elemSize, elemSize);
				}
			}
		}
	};

	const requestInterval = function(fn, delay) {
		const requestAnimFrame = window.requestAnimationFrame || ((callback) => setTimeout(() => callback(), 1000 / 60)); // aiming for 60 fps
		let start = new Date().getTime();
		const handle = {};
		function loop() {
			handle.value = requestAnimFrame(loop);
			const current = new Date().getTime();
			const delta = current - start;
			if (delta >= delay) {
				fn();
				start = new Date().getTime();
			}
		}
		handle.value = requestAnimFrame(loop);
		return handle;
	};

	const cancelInterval = (handle) => {
		const cancelFunc = requestAnimationFrame ? cancelAnimationFrame : clearTimeout;
		cancelFunc(handle.value);
	};

	useEffect(() => {
		let intervalHandler = {};
		if (running) {
			intervalHandler = requestInterval(() => {
				console.log(currIndex);
				currIndex++;
				if (currIndex < data.length) {
					draw(data[currIndex - 1], data[currIndex]);
				} else {
					cancelInterval(intervalHandler);
				}

				console.log(currIndex);
			}, interval);
		}
		return () => {
			if (intervalHandler && intervalHandler.value) cancelInterval(intervalHandler);
			if (currIndex > 1) onStop(currIndex);
		};
	}, [running]);

	useEffect(() => {
		drawBackground();
		drawFirstScene(data[0]);
	}, []);

	return (
		<div style={{ overflow: 'hidden' }}>
			<canvas
				id="background"
				width={width}
				height={height}
				style={{ position: 'absolute', zIndex: 0 }}
				ref={canvasBackground}
			></canvas>
			<canvas
				id="main"
				width={width}
				height={height}
				style={{ position: 'absolute', zIndex: 1 }}
				ref={canvasMain}
			></canvas>
		</div>
	);
};

export default Map;
