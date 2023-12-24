package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Requirements {
    @Id
    private String id;
    private String criterion;
//    @ManyToMany(mappedBy = "requirements", fetch = FetchType.EAGER)
//    private List<Dormitory> dormitories;
//
}
