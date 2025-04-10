package com.kafka.order.infrastructure.messaging;

import com.kafka.order.domain.entity.Outbox;
import com.kafka.order.domain.repository.OutboxRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "이벤트 발행")
@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {

    private final KafkaTemplate<String, String> outboxKafkaTemplate;
    private final OutboxRepository outboxRepository;


    @Transactional
    @Scheduled(fixedRate = 3000)
    public void sendOrderCreatedEvent() {
        List<Outbox> top100UnMarkedEvents = outboxRepository.findTop100UnMarkedEvents();

        for (Outbox event : top100UnMarkedEvents) {
            try {
                outboxKafkaTemplate.send(event.getEventType().getTopic(), event.getPayload());
                event.markAsSuccess();
            } catch (Exception e) {
                log.error("이벤트 발행 실패 = {}, 횟수 = {}", event.getEventType(), event.getRetryCount());
                event.increaseRetryCount();
            }
        }

    }
}
