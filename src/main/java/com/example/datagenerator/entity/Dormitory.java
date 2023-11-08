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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dormitory_requirements",
            joinColumns = @JoinColumn(name = "id_dormitory"),
            inverseJoinColumns = @JoinColumn(name = "id_requirement"))
    private Set<Requirements> requirements;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dormitory_services",
            joinColumns = @JoinColumn(name = "id_dormitory"),
            inverseJoinColumns = @JoinColumn(name = "id_additional_service"))
    private Set<AdditionalServices> additionalServices;
}
