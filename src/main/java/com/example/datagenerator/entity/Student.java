package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long academicYear;
    private String domicile;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_field_of_studies",
        joinColumns = @JoinColumn(name = "id_student"),
        inverseJoinColumns = @JoinColumn(name = "id_field_of_study"))
    private Set<FieldOfStudy> fieldOfStudies;
}
