package com.example.datagenerator.jpa.entity;


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
    private LocalDate start_date;
    private LocalDate end_date;
    @OneToOne
    @JoinColumn(name = "id_room")
    private Room room;
    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToMany(mappedBy = "contracts", fetch = FetchType.EAGER)
    private Set<Payment> payments;
}
