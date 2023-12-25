package com.example.datagenerator.mongoDB.model;

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
public class DormitorySecurity {

    private String id;
    private Dormitory dormitory;
    private Security security;
    private Long seniority;
    private Integer salary;

}