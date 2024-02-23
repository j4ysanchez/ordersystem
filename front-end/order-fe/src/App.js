import logo from "./logo.svg";
import "./App.css";

function App() {
  const handleOrder = () => {
    const url = "http://localhost:8080/orderReceived";
    const data = {
      pizzaType: "pepperoni",
      size: "medium",
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
      <button onClick={handleOrder}>Order Pizza</button>
    </div>
  );
}

export default App;
