package ca.mjsanchez.ordersystem;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;


public class OrderProcessor {
    
    public void processOrder(OrderCreatedEvent orderCreatedEvent) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");    
       




        System.out.println("Order received: " + orderCreatedEvent.getCustomer() + " " + orderCreatedEvent.getPizzaType() + " " + orderCreatedEvent.getSize());
        // publish the event to the "order-events" topic
        Gson gson = new Gson();
        String json = gson.toJson(orderCreatedEvent);
        System.out.println("Order processed: " + json);
    }

}
