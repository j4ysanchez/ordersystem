import { Select } from "antd";

import React, { useState, useEffect } from 'react';


// export function PizzaSizeDropdown(v, onChange) {
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
