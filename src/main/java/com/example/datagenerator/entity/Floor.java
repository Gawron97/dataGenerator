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
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long level;
    private String description;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "id_dormitory")
    private Dormitory dormitory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "floor_additional_rooms",
            joinColumns = @JoinColumn(name = "id_floor"),
            inverseJoinColumns = @JoinColumn(name = "id_additional_room"))
    private Set<AdditionalRooms> additionalRooms;
}
