import { Button, Card, Col, Form, Input, Row, Select, Modal } from "antd";
import React, { useState, useEffect } from "react";
import "./App.css";

import PizzaToppingsDropdown from "./PizzaToppings";
// import { PizzaSizeDropdown } from './PizzaSizes';

// export function PizzaSizeDropdown(v) {
//   const [pizzaSizes, setPizzaSizes] = useState([]);
//   const [pizzaSize, setPizzaSize] = useState('');

//   useEffect(() => {
//     fetch('http://localhost:8080/pizza-sizes')
//       .then(response => response.json())
//       .then(data => setPizzaSizes(data))
//       .catch(error => console.error('Error:', error));
//   }, []);

//   const handleChange = (newValue) => {
//     console.log(newValue);
//     console.log("v", v);
//     console.log("onChange", v.onChange);
//     setPizzaSize(newValue);

//   ;}

//   return (
//     <Select placeholder="Select a pizza size" onChange={handleChange}>
//       {Object.entries(pizzaSizes).map(([sizeKey, sizeValue]) =>
//         <Select.Option key={sizeKey} value={sizeKey}>{sizeValue}</Select.Option>
//       )}
//     </Select>
//   );
// }

function App() {
  const [form] = Form.useForm();
  const { Option } = Select;

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [pizzaType, setPizzaType] = useState("");
  const [pizzaSize, setPizzaSize] = useState("");
  const [pizzaSizes, setPizzaSizes] = useState([]);

  const [pizzaToppings, setPizzaToppings] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [orderData, setOrderData] = useState(null);

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

  const handleOrder = (values) => {
    const url = "http://localhost:8080/orderReceived";

    console.log("values", values);
    const data = {
      pizzaType: values.pizzaType,
      size: values.pizzaSize,
      customer: values.name,
      address: values.address,
    };

    console.log(data);

    fetch("http://localhost:8080/orderReceived", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => console.log(data))
      .catch((error) => console.error("Error:", error));

    setOrderData(data);
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <div>
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
                  { required: true, message: "Please input your address!" },
                ]}
              >
                <Input placeholder="Address" />
              </Form.Item>
              <Form.Item
                name="pizzaType"
                rules={[
                  { required: false, message: "Please select a pizza type!" },
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
                  { required: false, message: "Please select a pizza size!" },
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

      <Modal
        title="Order Received"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <p>Name: {orderData?.name}</p>
        <p>Address: {orderData?.address}</p>
        <p>Pizza Type: {orderData?.pizzaType}</p>
        <p>Pizza Size: {orderData?.size}</p>
      </Modal>
    </div>
  );
}

export default App;
