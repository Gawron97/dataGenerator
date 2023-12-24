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
public class FieldOfStudy {
    @Id
    private String id;
    private String name;
    private String level;
//    @ManyToOne
//    @JoinColumn(name = "id_faculty")
//    private Faculty faculty;
//    @ManyToMany(mappedBy = "fieldOfStudies", fetch = FetchType.EAGER)
//    private List<Student> students;
}
