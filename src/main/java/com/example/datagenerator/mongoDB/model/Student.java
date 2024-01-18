package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private LocalDate lastLogin;
    private Boolean isEnabled;
    private String contactNumber;
    private String additionalInfo;
    private Set<Payment> payments;
    private Set<Contract> contracts;
    private Set<Complain> complains;
    private Set<Studies> studies;
    private Set<Application> applications;
}
