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
public class AdditionalRooms {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_room_type")
    private AdditionalRoomType additionalRoomType;


    @ManyToMany(mappedBy = "additionalRooms", fetch = FetchType.EAGER)
    private List<Floor> floors;
}
