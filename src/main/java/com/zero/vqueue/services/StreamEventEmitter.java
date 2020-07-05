package com.zero.vqueue.services;

import com.zero.vqueue.services.facades.amqp.models.EventNotification;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
@Component
public class StreamEventEmitter {
    private ConnectableFlux<EventNotification> stream;
    private AtomicReference<FluxSink<EventNotification>> sinkEmitter;
    
    public StreamEventEmitter() {
        sinkEmitter = new AtomicReference<>();
        stream = Flux.<EventNotification>create(sink -> sinkEmitter.set(sink))
                .doOnSubscribe(subscription -> log.debug("::: Subscribing ... "))
                .publish();
    }
    
    public void emmitEvent(EventNotification eventNotification) {
        sinkEmitter.get().next(eventNotification);
    }
    
    public Flux<EventNotification> buildStream() {
        return stream.share();
    }
}
