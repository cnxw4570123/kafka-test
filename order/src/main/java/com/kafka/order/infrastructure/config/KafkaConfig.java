package com.kafka.order.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order.success", 3, (short) 1);
    }

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment.ready", 3, (short) 1);
    }

}
