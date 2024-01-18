package com.example.datagenerator.mongoDB.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private String id;
    private String roomNumber;
    private String description;
    private Long numberOfBeds;
    private boolean has_a_bathroom;
    private BigDecimal price;
    private BigDecimal size;
    private Long freeBeds;
    private Boolean isAvailable;
    private Module module;
    private RoomType roomType;
}
