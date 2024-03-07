import { Button, Card, Col, Form, Input, Row, Select, Modal } from "antd";
import React, { useState, useEffect } from "react";
import "./App.css";
import io from "socket.io-client";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import OrderReceivedModal from "./OrderReceivedModal";
import PizzaToppingsDropdown from "./PizzaToppings";
import OrderForm from "./OrderForm";
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

function Home() {
  return (
    <div>
      <h1>Home</h1>
    </div>
  );
}

function Orders() {
  return (
    <div>
      <h1>Orders</h1>
    </div>
  );
}

function App() {
  const [form] = Form.useForm();
  const { Option } = Select;

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [pizzaType, setPizzaType] = useState("");
  const [pizzaSize, setPizzaSize] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [orderData, setOrderData] = useState(null);

  const [showModal, setShowModal] = useState(false);
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    const socket = io("ws://localhost:8080/order-completed"); // replace with your server URL
    socket.on("order-completed", () => {
      setShowModal(true);
    });

    // Clean up the effect
    return () => socket.disconnect();
  }, []);

  const handleOrder = (values) => {
    const url = "http://localhost:8080/orderReceived";

    console.log("values", values);
    const data = {
      pizzaType: values.pizzaType,
      size: values.pizzaSize,
      name: values.name,
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
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/orders">Orders</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/"></Route>
        </Routes>
        <div>
          <OrderForm handleOrder={handleOrder} />
        </div>
        <OrderReceivedModal
          isModalVisible={isModalVisible}
          handleOk={handleOk}
          handleCancel={handleCancel}
          orderData={orderData}
        />

        {showModal && (
          <div className="modal">
            <Modal
              title="Order Status"
              visible={visible}
              onOk={handleOk}
              onCancel={handleCancel}
            >
              <p>Order was successful!</p>
            </Modal>
          </div>
        )}
      </div>
    </Router>
  );
}

export default App;
