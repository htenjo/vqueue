package com.zero.vqueue.services;

import com.zero.vqueue.services.business.QueueService;
import com.zero.vqueue.services.facades.amqp.NotificationService;
import com.zero.vqueue.services.facades.amqp.models.EventNotification;
import com.zero.vqueue.services.models.request.QueueRequest;
import com.zero.vqueue.services.models.response.QueueResponse;
import com.zero.vqueue.utils.LoggingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.zero.vqueue.services.facades.amqp.models.EventType.CREATE_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final QueueService queueService;
    private final NotificationService notificationService;
    private final StreamEventEmitter streamEventEmitter;
    
    public Mono<QueueResponse> createQueue(QueueRequest queueRequest) {
        return queueService.createQueue(queueRequest)
                .doOnError(error -> LoggingUtils.logError(log, queueRequest, error, "::: Error creating queue"))
                .doOnSuccess(queueResponse -> notificationService.sendNotification(CREATE_QUEUE, queueResponse));
    }
    
    public Flux<QueueResponse> listAll() {
        return queueService.listAll();
    }
    
    public void processEvent(EventNotification event) {
        streamEventEmitter.emmitEvent(event);
    }
    
    public Flux<EventNotification> streamEvents() {
        return streamEventEmitter.buildStream();
    }
}