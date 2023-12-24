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
public class Module {
    @Id
    private String id;
    private Boolean has_a_bathroom;
//    @ManyToOne
//    @JoinColumn(name = "id_module_type")
//    private ModuleType moduleType;
}
