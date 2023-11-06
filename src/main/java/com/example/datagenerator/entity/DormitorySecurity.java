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
public class DormitorySecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seniority;
    private String salary;
    @ManyToOne
    @JoinColumn(name = "id_dormitory")
    private Dormitory dormitory;
    @ManyToOne
    @JoinColumn(name = "id_security")
    private Security security;
}