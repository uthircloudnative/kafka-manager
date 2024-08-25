package com.platformtool.kafkamanager.config;


import com.platformtool.kafkamanager.model.UserProfile;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConfig
 *
 * @author Uthiraraj Saminathan
 */
@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, UserProfile> producerFactory() {

        Map<String, Object> kafkaProducerProperties = new HashMap<>();

        kafkaProducerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProducerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(kafkaProducerProperties);
    }

    @Bean
    public KafkaTemplate<String, UserProfile> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
