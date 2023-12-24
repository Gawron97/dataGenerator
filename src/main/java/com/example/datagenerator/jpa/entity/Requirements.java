package com.example.datagenerator.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Requirements {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String criterion;
    @ManyToMany(mappedBy = "requirements", fetch = FetchType.EAGER)
    private List<Dormitory> dormitories;
}
