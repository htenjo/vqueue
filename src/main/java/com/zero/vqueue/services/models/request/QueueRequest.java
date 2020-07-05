package com.zero.vqueue.services.models.request;

import java.time.LocalDate;
import lombok.Value;

@Value
public class QueueRequest {
    private String name;
    private String description;
    private LocalDate createdDated;
}