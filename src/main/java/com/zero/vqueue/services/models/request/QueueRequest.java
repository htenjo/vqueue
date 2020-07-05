package com.zero.vqueue.services.models.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
public class QueueRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDate createdDate;
}