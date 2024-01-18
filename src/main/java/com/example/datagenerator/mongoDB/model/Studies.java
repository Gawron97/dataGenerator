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
public class Studies {
    @Id
    private String id;
    private String fieldName;
    private String fieldLevel;
    private String facultyName;
    private String facultyDeanOfficeLocation;
    private String facultyContactNumber;
    private String facultyEmailAddress;
}
