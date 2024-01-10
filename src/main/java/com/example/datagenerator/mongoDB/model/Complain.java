package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document@NoArgsConstructor
@AllArgsConstructor
public class Complain {
    @Id
    private String id;
    private LocalDate submissionDate;
    private String author;
    private String title;
    private String description;
    @DBRef
    private Student student;
}
