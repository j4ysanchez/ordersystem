import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
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

@Controller
public class OrderCompletedListener {

    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/order-completed")
    @SendTo("/topic/order-completed")
    public String orderCompleted(String message) throws Exception {
        return message;
    }

    // private final SimpMessagingTemplate template;

    // public OrderCompletedListener(SimpMessagingTemplate template) {
    // this.template = template;
    // }

    // @KafkaListener(topics = "order-completed-events")
    // public void listen(ConsumerRecord<?, ?> record) {
    // template.convertAndSend("/topic/order-completed", record.value());
    // }
    // public OrderCompletedListener() {
    // System.out.println("------------ Constructor");
    // Properties props = new Properties();
    // props.put(StreamsConfig.APPLICATION_ID_CONFIG, "join-example");
    // props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    // props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());
    // props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());
    // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    // StreamsBuilder builder = new StreamsBuilder();
    // KTable<String, String> orderEvents = builder.table("order-completed-events",
    // Materialized.as("order-store"));

    // // Filter the KTables
    // KTable<String, String> filteredOrderEvents = orderEvents.filter((key, value)
    // -> {
    // if (value != null) {

    // System.out.println("Listener order-events: " + value);
    // System.out.println("Listener order-events key: " + key);
    // JsonObject json = JsonParser.parseString(value).getAsJsonObject();
    // boolean id = json.has("id");
    // System.out.println("has id:" + id);
    // return json.has("id");
    // }
    // return false;
    // });

    // filteredOrderEvents.toStream()
    // .foreach((key, value) -> System.out.println("filteredOrderEvents Key: " + key
    // + " Value: " + value));

    // KafkaStreams streams = new KafkaStreams(builder.build(), props);
    // streams.start();
    // }
}