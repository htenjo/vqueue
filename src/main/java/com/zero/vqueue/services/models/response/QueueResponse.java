package com.zero.vqueue.services.models.response;

import java.time.LocalDate;
import lombok.Value;

@Value
public class QueueResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdDated;
}
