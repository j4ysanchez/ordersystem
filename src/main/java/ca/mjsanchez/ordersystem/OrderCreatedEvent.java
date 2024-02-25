package ca.mjsanchez.ordersystem;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class OrderCreatedEvent {

    private static final AtomicInteger count = new AtomicInteger(0);
    private String id;
    private String customer;
    private String pizzaType;
    private String size;
    private String address;

    public OrderCreatedEvent(String customer, String pizzaType, String size, String address) {
        // this.id = count.incrementAndGet();
        this.id = String.valueOf(System.currentTimeMillis());
        this.customer = customer;
        this.pizzaType = pizzaType;
        this.size = size;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String customerAddress) {
        this.address = customerAddress;
    }

    public String getId() {
        return id;
    }

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

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
