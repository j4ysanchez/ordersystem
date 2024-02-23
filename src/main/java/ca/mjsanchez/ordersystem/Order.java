package ca.mjsanchez.ordersystem;

public class Order {
    private String customer;
    private String pizzaType;
    private String size;
    
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getPizzaType() {
        return pizzaType;
    }
    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    
}