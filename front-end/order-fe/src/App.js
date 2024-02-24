import logo from "./logo.svg";
import "./App.css";
import { Form, Input, Select, Radio, Button, Row, Col, Card } from 'antd';
import React, { useState } from 'react';



function App() {
  const [form] = Form.useForm();
  const { Option } = Select;

  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [pizzaType, setPizzaType] = useState('');
  const [pizzaSize, setPizzaSize] = useState('');

  const handleOrder = () => {
    const url = "http://localhost:8080/orderReceived";
    const data = {
      pizzaType: "pepperoni",
      size: "medium",
      customer: "Jason"
    };

    fetch("http://localhost:8080/orderReceived", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
  };

  return (
    <Row justify="center" align="middle" style={{ minHeight: '100vh' }}>
      <Col xs={24} sm={16} md={12} lg={10} xl={8}>
        <Card>
          <Form onFinish={handleOrder}>
            <Form.Item name="name" rules={[{ required: true, message: 'Please input your name!' }]}>
              <Input placeholder="Name" />
            </Form.Item>
            <Form.Item name="address" rules={[{ required: true, message: 'Please input your address!' }]}>
              <Input placeholder="Address" />
            </Form.Item>
            <Form.Item name="pizzaType" rules={[{ required: true, message: 'Please select a pizza type!' }]}>
              <Select placeholder="Pizza Type">
                <Option value="margherita">Margherita</Option>
                <Option value="pepperoni">Pepperoni</Option>
                <Option value="hawaiian">Hawaiian</Option>
                {/* Add more options as needed */}
              </Select>
            </Form.Item>
            <Form.Item name="pizzaSize" rules={[{ required: true, message: 'Please select a pizza size!' }]}>
              <Radio.Group>
                <Radio value="small">Small</Radio>
                <Radio value="medium">Medium</Radio>
                <Radio value="large">Large</Radio>
              </Radio.Group>
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit" block>
                Submit
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </Col>
    </Row>
  );
}

export default App;
