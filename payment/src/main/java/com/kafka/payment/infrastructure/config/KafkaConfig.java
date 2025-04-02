package com.kafka.payment.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

    public static final String PAYMENT_READY = "payment.ready";

    @Bean
    public NewTopic paymentReadyTopic() {
        return new NewTopic(PAYMENT_READY, 3, (short) 1);
    }

}
