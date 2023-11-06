package com.example.datagenerator.entity;

import jakarta.persistence.*;
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
    private String name;
    private String city;
    private String street;
    private String numberOfStreet;
    private String description;
    private String contactInfo;
    @ManyToOne
    @JoinColumn(name = "id_statute")
    private Statute statute;
    @ManyToOne
    @JoinColumn(name = "id_manager")
    private Manager manager;
}
