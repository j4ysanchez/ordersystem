import React from "react";
import {Table} from "antd";

function AllOrders() {

  const data = [
    {
        key: '1',
        id: '1711079799137',
        customer: 'Jason',
        pizzaType: 'cheese',
        size: 'large',
        address: 'Calgary',
        timestamp: new Date(parseInt('1711079799137')).toLocaleString(),
    },
    // Add more orders here...
];

const columns = [
  {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
  },
  {
      title: 'Customer',
      dataIndex: 'customer',
      key: 'customer',
  },
  {
      title: 'Pizza Type',
      dataIndex: 'pizzaType',
      key: 'pizzaType',
  },
  {
      title: 'Size',
      dataIndex: 'size',
      key: 'size',
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
  },
];

return (
<div>
            <h1>All Orders</h1>
            <Table columns={columns} dataSource={data} />
        </div>
    );
  }

  export default AllOrders;