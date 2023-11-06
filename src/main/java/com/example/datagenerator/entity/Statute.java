package com.example.datagenerator.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Statute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String additional_info;

//    @OneToMany(mappedBy = "roomType")
//    @ToString.Exclude
//    private List<Dormitory> dormitories;

}
