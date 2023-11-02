package com.example.datagenerator.entity;

import com.github.javafaker.App;
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
public class OfficeWorker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long seniority;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany
    @JoinColumn(name = "id_application")
    private List<Application> application;
}
