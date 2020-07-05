package com.zero.vqueue.services.models.persistence;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Document
public class Queue {
    @Id
    private String id = UUID.randomUUID().toString();
    
    @NotNull
    @Field
    private String name;
    
    @NotNull
    @Field
    private String description;
    
    @NotNull
    @Field
    private LocalDate createdDate;
}
