import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Orders = () => {
    const [orders, setOrders] = useState([]);

    // useEffect(() => {
    //     const fetchOrders = async () => {
    //         try {
    //             const response = await axios.get('/api/orders');
    //             setOrders(response.data);
    //         } catch (error) {
    //             console.error('Error fetching orders:', error);
    //         }
    //     };

    //     fetchOrders();
    // }, []);

    return (
        <div>
            <h2>Orders</h2>
            {orders.map((order, index) => (
                <div key={index}>
                    <p>Order ID: {order.id}</p>
                    <p>Order Details: {order.details}</p>
                    {/* Add more fields as necessary */}
                </div>
            ))}
        </div>
    );
};

export default Orders;