package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OfficeWorker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long seniority;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
