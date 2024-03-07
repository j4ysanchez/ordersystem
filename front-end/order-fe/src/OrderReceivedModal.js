import { Modal } from 'antd';
import React from 'react';

function OrderReceivedModal({ isModalVisible, handleOk, handleCancel, orderData }) {
  return (
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
  );
}

export default OrderReceivedModal;