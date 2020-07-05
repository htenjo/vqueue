package com.zero.vqueue.controllers;

import com.zero.vqueue.services.ManagerService;
import com.zero.vqueue.services.models.request.QueueRequest;
import com.zero.vqueue.services.models.response.QueueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class QueueController {
    private final ManagerService managerService;
    
    @PostMapping("/queue")
    public Mono<QueueResponse> createQueue(@RequestBody QueueRequest queueRequest) {
        return managerService.createQueue(queueRequest);
    }
    
    @GetMapping("/queue")
    public Flux<QueueResponse> listAllQueues() {
        return managerService.listAll();
    }
    
//    public Flux<>
}