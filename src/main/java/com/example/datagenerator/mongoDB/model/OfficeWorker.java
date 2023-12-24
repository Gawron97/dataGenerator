package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class OfficeWorker {
    @Id
    private String id;
    private Long seniority;
//    @OneToOne
//    @JoinColumn(name = "id_user")
//    private User user;
//
}