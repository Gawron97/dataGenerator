package com.example.datagenerator.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalRoomType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
}
