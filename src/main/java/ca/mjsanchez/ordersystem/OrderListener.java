package ca.mjsanchez.ordersystem;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.apache.kafka.streams.kstream.Printed;


@Service
public class OrderListener {

    private KafkaStreams streams;

    @PostConstruct
    public void start() {

        System.out.println("**** OrderListener start() called ****");
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-listener" + System.currentTimeMillis());
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // KStream<String, String> orderEvents = streamsBuilder.stream("order-events");
        // orderEvents.foreach((key, value) -> {
        //     System.out.println("*** Event detected: Key: " + key + " Value: " + value);
        // });
        

        // orderEvents.print(Printed.<String, String>toSysOut().withLabel("**Order Events"));

        KTable<String, String> orderEventsTable = streamsBuilder.table("order-events");

        // You can now use the KTable for various operations, for example:
        orderEventsTable.toStream().foreach((key, value) -> {
            System.out.println("-*- Event detected in KTable: Key: " + key + " Value: " + value);
        });

        streams = new KafkaStreams(streamsBuilder.build(), config);
        streams.start();


        // KStream<String, String> orderEvents = streams.stream("order-completed-events");
    }

    @PreDestroy
    public void stop() {
        streams.close();
    }
    
}