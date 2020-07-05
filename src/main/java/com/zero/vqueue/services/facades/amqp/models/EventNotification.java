package com.zero.vqueue.services.facades.amqp.models;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class EventNotification {
    private EventType type;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    private String payload;
}