package com.example.datagenerator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    @JoinColumn(name = "id_room")
    private Room room;
    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;
    @ManyToMany
    @JoinColumn(name = "id_payment")
    private Set<Payment> payments;
}
