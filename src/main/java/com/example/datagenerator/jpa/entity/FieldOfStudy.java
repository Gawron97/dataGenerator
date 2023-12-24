package com.example.datagenerator.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FieldOfStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String level;
    @ManyToOne
    @JoinColumn(name = "id_faculty")
    private Faculty faculty;
    @ManyToMany(mappedBy = "fieldOfStudies", fetch = FetchType.EAGER)
    private List<Student> students;
}
