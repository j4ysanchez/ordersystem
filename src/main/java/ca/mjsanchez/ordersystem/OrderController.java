package ca.mjsanchez.ordersystem;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class OrderController {

    List<String> allOrders;
    String uuid;
    private KafkaConsumer<String, String> consumer;


    public OrderController() {
        allOrders = new ArrayList<>();
        uuid = UUID.randomUUID().toString();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", uuid);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("order-events"));

        // ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

        // for (ConsumerRecord<String, String> record : records) {
        //     allOrders.add(record.value());
        // }

        // consumer.close();
        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    allOrders.add(record.value());
                }
            }
        }).start();

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/orderReceived")
    public ResponseEntity<String> orderReceived(@RequestBody Order order) {
        String customer = order.getName();
        String pizzaType = order.getPizzaType();
        String size = order.getSize();
        String address = order.getAddress();

        System.out.println("POST Order received: " + customer + " " + pizzaType + " " + size);

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(customer, pizzaType, size, address);

        Gson gson = new Gson();
        String json = gson.toJson(orderCreatedEvent);

        // publish the event to the "order-events" topic

        // Create a unique id for a ProducerRecord key
        String recordKey = String.valueOf(System.currentTimeMillis());
        System.out.println("recordKey " + recordKey);

        producer.send(new ProducerRecord<String, String>("order-events", recordKey, json));

        producer.close();

        return ResponseEntity.status(HttpStatus.CREATED).body("{data: 'Order Received'}");

    }

    @GetMapping("/hello-event")
    @CrossOrigin(origins = "http://localhost:3000")
    public void sendHelloEvent(@RequestParam String id) {

        String template = "{ \"orderid\":\"%s\", \"data\":\"hello%s\"}";
        String json = String.format(template, id, id);
        Gson gson = new Gson();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<String, String>("hello-log", "key" + id, json));

        producer.close();
    }

    @GetMapping("/bye-event")
    public void sendByeEvent(@RequestParam String id) {
        String template = "{ \"orderid\":\"%s\", \"data\":\"bye%s\"}";
        String json = String.format(template, id, id);
        Gson gson = new Gson();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<String, String>("bye-log", "key" + id, json));

        producer.close();
    }

    @GetMapping("/orderReceived")
    @CrossOrigin(origins = "http://localhost:3000")
    public OrderCreatedEvent orderReceived(
            @RequestParam(name = "customer", defaultValue = "John Doe") String customer,
            @RequestParam(name = "pizzaType", defaultValue = "Pepperoni") String pizzaType,
            @RequestParam(name = "size", defaultValue = "Medium") String size,
            @RequestParam(name = "address", defaultValue = "123 Main St") String address) {

        System.out.println("Order received: " + customer + " " + pizzaType + " " + size);

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(customer, pizzaType, size, address);

        Gson gson = new Gson();
        String json = gson.toJson(orderCreatedEvent);

        // Create a unique id for a ProducerRecord key
        String recordKey = String.valueOf(System.currentTimeMillis());
        System.out.println("recordKey " + recordKey);

        // publish the event to the "order-events" topic
        producer.send(new ProducerRecord<String, String>("order-events", recordKey, json));

        producer.close();

        return orderCreatedEvent;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewOrdersPlaced")
    public ResponseEntity<String> viewOrders() {


        // Properties props = new Properties();
        // props.put("bootstrap.servers", "localhost:9092");
        // props.put("group.id", uuid);
        // props.put("key.deserializer", StringDeserializer.class.getName());
        // props.put("value.deserializer", StringDeserializer.class.getName());
        // props.put("auto.offset.reset", "earliest");

        // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // consumer.subscribe(Arrays.asList("order-events"));

        // ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

        // for (ConsumerRecord<String, String> record : records) {
        //     allOrders.add(record.value());
        // }

        // consumer.close();


        Gson gson = new Gson();

        allOrders.sort((jsonString1, jsonString2) -> {
            JsonObject jsonObject1 = gson.fromJson(jsonString1, JsonObject.class);
            JsonObject jsonObject2 = gson.fromJson(jsonString2, JsonObject.class);
        
            String timestamp1 = jsonObject1.get("timestamp").getAsString();
            String timestamp2 = jsonObject2.get("timestamp").getAsString();
        
            return timestamp2.compareTo(timestamp1);
        });

        return ResponseEntity.ok(gson.toJson(allOrders));

    }

}
