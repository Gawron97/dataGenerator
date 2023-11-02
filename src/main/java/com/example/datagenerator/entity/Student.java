package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long academicYear;
    private String domicile;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany
    @JoinColumn(name = "id_application")
    private List<Application> application;
}
