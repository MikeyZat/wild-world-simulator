import React from 'react';
import { Slider, Col, Row, InputNumber } from 'antd';

const SliderInput = (props) => {
    const onSliderChange = (value) => props.onChange(value);
    return (
    <Row>
        <Col span={6}>
        <Slider
            min={100}
            max={1000}
            onChange={onSliderChange}
            value={typeof props.value === 'number' ? props.value : 0}
            disabled={props.isSimulationRunning}
        />
        </Col>
        <Col span={2}>
        <InputNumber
            min={100}
            max={1000}
            style={{ marginLeft: 16 }}
            value={props.value}
            onChange={onSliderChange}
            disabled={props.isSimulationRunning}
        />
        </Col>
    </Row>
  );
}

export default SliderInput;