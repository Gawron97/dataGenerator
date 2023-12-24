package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private String id;
    private Long academicYear;
    private String domicile;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;

//    @OneToOne
//    @JoinColumn(name = "id_user")
//    private User user;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "student_field_of_studies",
//        joinColumns = @JoinColumn(name = "id_student"),
//        inverseJoinColumns = @JoinColumn(name = "id_field_of_study"))
//    private Set<FieldOfStudy> fieldOfStudies;
}
