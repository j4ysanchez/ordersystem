package ca.mjsanchez.ordersystem.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public Topology kafkaStreamTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, String> orderEvents = streamsBuilder.stream("order-events");

        orderEvents.foreach((key, value) -> {
            System.out.println("*** Event detected: Key: " + key + " Value: " + value);
        });

        return streamsBuilder.build();
    }
}
