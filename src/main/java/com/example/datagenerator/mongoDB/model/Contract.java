package com.example.datagenerator.mongoDB.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    private String id;
    private LocalDate start_date;
    private LocalDate end_date;
    @DBRef
    private Room room;
    private Set<Payment> payments;
}
