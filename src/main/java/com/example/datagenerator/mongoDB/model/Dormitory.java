package com.example.datagenerator.mongoDB.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {
    @Id
    private String id;
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
    private Statute statute;
    private Manager manager;
    private Set<Requirements> requirements;
    private Set<AdditionalServices> additionalServices;
    private Set<Security> securities;
    private Set<Floor> floors;

}
