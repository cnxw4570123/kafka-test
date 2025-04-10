package com.kafka.order.infrastructure.repository;


import com.kafka.order.domain.entity.Outbox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutboxJpaRepository extends JpaRepository<Outbox, Long> {

    @Query("select o from Outbox o where o.retryCount < 3 order by o.createdAt asc limit 100")
    List<Outbox> findTop100UnMarkedEvents();

}
