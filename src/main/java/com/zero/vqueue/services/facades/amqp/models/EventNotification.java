package com.zero.vqueue.services.facades.amqp.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventNotification {
    private EventType type;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    private String payload;
}