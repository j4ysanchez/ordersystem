import React, { useState, useEffect } from "react";
import { Table } from "antd";

function StringComparator(a, b) {
  if (a === null || a === undefined) return -1;
  if (b === null || b === undefined) return 1;

  return a.localeCompare(b);
  
}

function AllOrders() {

  //   const data = [
  //     {
  //         key: '1',
  //         id: '1711079799137',
  //         customer: 'Jason',
  //         pizzaType: 'cheese',
  //         size: 'large',
  //         address: 'Calgary',
  //         timestamp: new Date(parseInt('1711079799137')).toLocaleString(),
  //     },
  //     // Add more orders here...
  // ];

  const [orderData, setOrderData] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/viewOrdersPlaced', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => { setOrderData(data); console.log(data); console.log(data.length) })
      .catch((error) => console.error("Error:", error));
  }, []);

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      sorter:(a, b) => a.id - b.id,
      sortDirections:[  'descend', 'ascend']
    },
    {
      title: 'Customer',
      dataIndex: 'customer',
      key: 'customer',
      sorter:(a, b) => {
          return StringComparator(a.customer, b.customer);
      },
    },
    {
      title: 'Pizza Type',
      dataIndex: 'pizzaType',
      key: 'pizzaType',
      sorter:(a, b) => {
          return StringComparator(a.pizzaType, b.pizzaType);
      },  
    },
    {
      title: 'Size',
      dataIndex: 'size',
      key: 'size',
      sorter:(a, b) => {
        return StringComparator(a.size, b.size);
      }
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'Timestamp',
      dataIndex: 'timestamp',
      key: 'timestamp',
      sorter:(a, b) => new Date(a.timestamp) - new Date(b.timestamp),
      sortDirections:[  'descend', 'ascend']
    },
  ];

  const mydata = [
    { "id": "1711079795579", "customer": "Jason", "pizzaType": "cheese", "size": "medium", "address": "Calgary", "timestamp": "1711079795579" },
    { "id": "1711079799137", "customer": "Jason", "pizzaType": "cheese", "size": "large", "address": "Calgary", "timestamp": "1711079799137" }
  ]
  const string1 = orderData[0];

  // for (let i = 0; i < orderData.length; i++) {
  //   console.log(JSON.parse(orderData[i]));
  // }

  const orderJsonList = orderData.map(jsonString => {
    try {
      let jsonObject = JSON.parse(jsonString);

      jsonObject.timestamp = new Date(Number(jsonObject.timestamp)).toLocaleString();
      return jsonObject;
    } catch (e) {
      console.error('Invalid JSON:', jsonString);
      return null;
    }
  }).filter(item => item); // remove null items

  return (
    <div>
      <h1>All Orders</h1>
      <Table dataSource={orderJsonList} columns={columns} />

    </div>
  );
}

export default AllOrders;