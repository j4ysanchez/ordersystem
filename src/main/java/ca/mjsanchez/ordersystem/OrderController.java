package ca.mjsanchez.ordersystem;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class OrderController {


    
    @GetMapping("/orderReceived")
    public OrderCreatedEvent orderReceived(
        @RequestParam(name = "customer", defaultValue = "John Doe") String customer,
        @RequestParam(name = "pizzaType", defaultValue = "Pepperoni") String pizzaType,
        @RequestParam(name = "size", defaultValue = "Medium") String size
    ) {

        System.out.println("Order received: " + customer + " " + pizzaType + " " + size);

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");    

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

    
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(customer, pizzaType, size);
        
        Gson gson = new Gson();
        String json = gson.toJson(orderCreatedEvent);

        // publish the event to the "order-events" topic
        producer.send(new ProducerRecord<String, String>("order-events", json));

        producer.close();

        return orderCreatedEvent;
    }

    
}