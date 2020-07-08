package com.zero.vqueue.controllers;

import com.zero.vqueue.services.ManagerService;
import com.zero.vqueue.services.facades.amqp.models.EventNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QueueRSocketController {
    private final ManagerService managerService;
    
    @MessageMapping("queue-stream-event")
    public Flux<EventNotification> getStreamEvents() {
        log.info("::: Received queue-stream-event request");
        return managerService.streamEvents();
    }
}
