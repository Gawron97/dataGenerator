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
public class AdditionalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinColumn(name = "id_dormitory")
    private Set<Dormitory> dormitories;

    @ManyToMany
    @JoinColumn(name = "id_additional_rooms")
    private Set<AdditionalRooms> additionalRooms;
}
