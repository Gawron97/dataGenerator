package com.example.datagenerator.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String description;
    private Long numberOfBeds;
    private String hasABathroom;
    private String price;
    private String size;
    private Long freeBeds;
    private String isAvailable;
    private Long idFloor;
    private Long idModule;
    private Long idRoomType;
}
