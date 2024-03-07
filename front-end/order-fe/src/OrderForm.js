import { Button, Card, Col, Form, Input, Row, Select, Modal } from "antd";
import PizzaToppingsDropdown from "./PizzaToppings";
import React, { useState, useEffect } from "react";

function OrderForm({ handleOrder }) {
  const [pizzaSizes, setPizzaSizes] = useState([]);

  const [pizzaToppings, setPizzaToppings] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/pizza-toppings")
      .then((response) => response.json())
      .then((data) => setPizzaToppings(data))
      .catch((error) => console.error("Error:", error));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/pizza-sizes")
      .then((response) => response.json())
      .then((data) => setPizzaSizes(data))
      .catch((error) => console.error("Error:", error));
  }, []);

  return (
    <Row justify="center" align="middle" style={{ minHeight: "100vh" }}>
      <Col xs={24} sm={16} md={12} lg={10} xl={8}>
        <Card>
          <Form onFinish={handleOrder}>
            <Form.Item
              name="name"
              rules={[{ required: true, message: "Please input your name!" }]}
            >
              <Input placeholder="Name" />
            </Form.Item>
            <Form.Item
              name="address"
              rules={[
                {
                  required: true,
                  message: "Please input your address!",
                },
              ]}
            >
              <Input placeholder="Address" />
            </Form.Item>
            <Form.Item
              name="pizzaType"
              rules={[
                {
                  required: false,
                  message: "Please select a pizza type!",
                },
              ]}
            >
              <Select placeholder="Select your toppings">
                {Object.entries(pizzaToppings).map(([key, value]) => (
                  <option key={key} value={key}>
                    {value}
                  </option>
                ))}
              </Select>
            </Form.Item>
            <Form.Item
              name="pizzaSize"
              rules={[
                {
                  required: false,
                  message: "Please select a pizza size!",
                },
              ]}
            >
              <Select placeholder="Select a pizza size">
                {Object.entries(pizzaSizes).map(([sizeKey, sizeValue]) => (
                  <Select.Option key={sizeKey} value={sizeKey}>
                    {sizeValue}
                  </Select.Option>
                ))}
              </Select>
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

export default OrderForm;
