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
public class AdditionalRooms {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_room_type")
    private AdditionalRoomType additionalRoomType;

    @ManyToMany
    @JoinColumn(name = "id_floor")
    private Set<Floor> floors;
}
