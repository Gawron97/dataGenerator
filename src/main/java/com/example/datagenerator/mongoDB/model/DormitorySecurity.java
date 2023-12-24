package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    @EmbeddedId
    private DormitorySecurityId id;
    private Long seniority;
    private Integer salary;

}