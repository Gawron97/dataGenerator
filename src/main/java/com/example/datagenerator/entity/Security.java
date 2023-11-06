package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qualifications;
    private String workSchedule;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
