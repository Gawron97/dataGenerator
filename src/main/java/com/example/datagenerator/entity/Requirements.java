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
public class Requirements {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String criterion;
    @ManyToMany
    @JoinColumn(name = "id_dormitory")
    private Set<Dormitory> dormitories;
}
