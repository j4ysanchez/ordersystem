import logo from "./logo.svg";
import "./App.css";
import { Button } from 'antd';



function App() {
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
      .then((response) => {
        console.log(response);
      })
      .then((response) => response.json());
  };

  return (
    <div className="App">
      <Button type="primary" onClick={handleOrder}>Click to Order Pizza</Button>
      <Button type="primary" onClick={handleOrder}>Second Button</Button>
    </div>
  );
}

export default App;
