package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private LocalDate lastLogin;
    private Boolean isEnabled;
    private String contactNumber;
    private String additionalInfo;
    @OneToOne(mappedBy = "user")
    private Manager manager;

}
