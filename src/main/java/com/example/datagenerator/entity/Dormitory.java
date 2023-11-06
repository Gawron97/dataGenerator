package com.example.datagenerator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @ManyToMany
    @JoinColumn(name = "id_requirements")
    private Set<Requirements> requirements;

    @ManyToMany
    @JoinColumn(name = "id_additional_services")
    private Set<AdditionalServices> additionalServices;
}
