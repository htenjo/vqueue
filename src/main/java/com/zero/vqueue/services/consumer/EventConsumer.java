package com.zero.vqueue.services.consumer;

import com.zero.vqueue.services.ManagerService;
import com.zero.vqueue.services.facades.amqp.models.EventNotification;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {
    private final ManagerService managerService;
    
    @RabbitListener(queues = "${vqueue.amqp.eventQueueName}")
    public void consumeEventMessage(@Valid @Payload EventNotification eventNotification) {
        log.info("::: Consuming new EventNotification [{}]", eventNotification);
        managerService.processEvent(eventNotification);
    }
}
