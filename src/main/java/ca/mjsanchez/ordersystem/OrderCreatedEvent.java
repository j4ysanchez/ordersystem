package ca.mjsanchez.ordersystem;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class OrderCreatedEvent {

    private static final AtomicInteger count = new AtomicInteger(0); 
    private int id;
    private String customer;
    private String pizzaType;
    private String size;

    public OrderCreatedEvent(String customer, String pizzaType, String size) {
        this.id = count.incrementAndGet();
        this.customer = customer;
        this.pizzaType = pizzaType;
        this.size = size;
    }

    public int getId() {
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
