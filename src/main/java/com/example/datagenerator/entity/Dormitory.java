package com.example.datagenerator.entity;

<<<<<<< Updated upstream
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
=======

import jakarta.persistence.*;
>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String numberOfStreet;

    private String description;
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "id_statute", referencedColumnName = "id")
    private Statute statute;

    @ManyToOne
    @JoinColumn(name = "id_manager", referencedColumnName = "id")
    private Manager manager;


}
