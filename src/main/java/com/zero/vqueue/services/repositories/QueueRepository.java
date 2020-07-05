package com.zero.vqueue.services.repositories;

import com.zero.vqueue.services.models.persistence.Queue;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends ReactiveCrudRepository<Queue, Long> {
}
