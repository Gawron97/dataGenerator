package com.example.datagenerator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String description;
    private Long numberOfBeds;
    private Boolean hasABathroom;
    private BigDecimal price;
    private BigDecimal size;
    private Long freeBeds;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "id_floor")
    private Floor floor;
    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;
    @ManyToOne
    @JoinColumn(name = "id_room_type")
    private RoomType roomType;
}
