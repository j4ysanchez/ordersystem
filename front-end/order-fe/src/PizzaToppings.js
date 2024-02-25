import logo from "./logo.svg";
import "./App.css";
import { Form, Input, Select, Radio, Button, Row, Col, Card } from 'antd';
import React, { useState, useEffect } from 'react';

// function PizzaToppingsDropdown() {
//   const [pizzaToppings, setPizzaToppings] = useState([]);

//   useEffect(() => {
//       fetch('http://localhost:8080/pizza-toppings')
//           .then(response => response.json())
//           .then(data => setPizzaToppings(data))
//           .catch(error => console.error('Error:', error));
//   }, []);

//   return (
//       <Select placeholder="Select your toppings">
//           {Object.entries(pizzaToppings).map(([key, value]) => 
//               <option key={key} value={key}>{value}</option>
//           )}
//       </Select>
//   );
// }

// export default PizzaToppingsDropdown;
