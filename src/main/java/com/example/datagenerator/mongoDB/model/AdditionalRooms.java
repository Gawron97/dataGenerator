package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalRooms {

    @Id
    private String id;
    private int roomNumber;
    private String description;
    private AdditionalRoomType additionalRoomType;
}
