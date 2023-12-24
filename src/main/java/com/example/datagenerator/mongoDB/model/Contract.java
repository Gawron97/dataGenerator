package com.example.datagenerator.mongoDB.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    private String id;
    private LocalDate start_date;
    private LocalDate end_date;
//    @OneToOne
//    @JoinColumn(name = "id_room")
//    private Room room;
//    @OneToOne
//    @JoinColumn(name = "id_student")
//    private Student student;
//
//    @ManyToMany(mappedBy = "contracts", fetch = FetchType.EAGER)
//    private Set<Payment> payments;
}
