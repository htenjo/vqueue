package com.zero.vqueue.services.models.persistence;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Document
public class Queue {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private Long id;
    private String name;
    private String description;
    private LocalDate createdDated;
}
