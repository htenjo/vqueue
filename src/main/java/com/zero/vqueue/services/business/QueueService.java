package com.zero.vqueue.services.business;

import com.zero.vqueue.services.models.persistence.Queue;
import com.zero.vqueue.services.models.request.QueueRequest;
import com.zero.vqueue.services.models.response.QueueResponse;
import com.zero.vqueue.services.repositories.QueueRepository;
import com.zero.vqueue.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final QueueRepository repository;
    
    public Mono<QueueResponse> createQueue(QueueRequest queueRequest) {
        Queue queue = ObjectMapperUtil.map(queueRequest, Queue.class);
        return repository.save(queue)
                .map(q -> ObjectMapperUtil.map(q, QueueResponse.class));
    }
    
    public Flux<QueueResponse> listAll() {
        return repository.findAll()
                .map(q -> ObjectMapperUtil.map(q, QueueResponse.class));
    }
}
