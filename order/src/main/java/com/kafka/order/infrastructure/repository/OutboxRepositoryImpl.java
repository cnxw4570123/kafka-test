package com.kafka.order.infrastructure.repository;

import com.kafka.order.domain.entity.Outbox;
import com.kafka.order.domain.repository.OutboxRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxJpaRepository outboxJpaRepository;

    @Override
    public Outbox save(Outbox outbox) {
        return outboxJpaRepository.save(outbox);
    }

    @Override
    public List<Outbox> findTop100UnMarkedEvents() {
        return outboxJpaRepository.findTop100UnMarkedEvents();
    }
}
