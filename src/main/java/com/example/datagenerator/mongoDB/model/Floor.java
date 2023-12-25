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
public class Floor {
    @Id
    private String id;
    private Long level;
    private String description;
    private Boolean isAvailable;
    private Dormitory dormitory;
    private Set<AdditionalRooms> additionalRooms;
}
