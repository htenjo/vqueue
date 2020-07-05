package com.zero.vqueue.services.models.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueueResponse {
    private String id;
    private String name;
    private String description;
    private LocalDate createdDate;
}
