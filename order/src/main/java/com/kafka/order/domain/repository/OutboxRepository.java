package com.kafka.order.domain.repository;

import com.kafka.order.domain.entity.Outbox;
import java.util.List;

public interface OutboxRepository {

    Outbox save(Outbox outbox);

    List<Outbox> findTop100UnMarkedEvents();
}
