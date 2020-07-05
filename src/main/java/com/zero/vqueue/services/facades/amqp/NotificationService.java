package com.zero.vqueue.services.facades.amqp;

import com.zero.vqueue.services.facades.amqp.config.AmqpConfig;
import com.zero.vqueue.services.facades.amqp.models.EventNotification;
import com.zero.vqueue.services.facades.amqp.models.EventType;
import com.zero.vqueue.utils.LoggingUtils;
import com.zero.vqueue.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.zero.vqueue.utils.LoggingUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final AmqpTemplate amqpTemplate;
    private final AmqpConfig amqpConfig;
    
    public <T> Mono<Void> sendNotification(EventType eventType, T notificationMessage) {
        EventNotification payload = EventNotification.builder()
                .payload(ObjectMapperUtil.mapObjectToJson(notificationMessage))
                .type(eventType)
                .build();
        
        return Mono.fromRunnable(() -> amqpTemplate.convertAndSend(amqpConfig.getEventExchangeName(), payload))
                .doOnSuccess(any -> log.debug("::: Notification event sent"))
                .doOnError(error -> logError(log, error, "::: Notification with errors", error.getMessage()))
                .then()
                .subscribeOn(Schedulers.elastic());
    }
}
