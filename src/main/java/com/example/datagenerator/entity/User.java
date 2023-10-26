package com.example.datagenerator.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private LocalDate lastLogin;
    private String isEnabled;
    private String contactNumber;
    private String additionalInfo;
    @OneToOne(mappedBy = "user")
    private Manager manager;

}
