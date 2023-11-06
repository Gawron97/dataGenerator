package com.example.datagenerator.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
//    @OneToMany(mappedBy = "roomType")
//    @ToString.Exclude
//    private List<Room> rooms;
}
