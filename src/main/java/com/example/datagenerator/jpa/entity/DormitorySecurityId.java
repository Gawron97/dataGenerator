package com.example.datagenerator.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class DormitorySecurityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_dormitory")
    private Dormitory dormitoryId;
    @ManyToOne
    @JoinColumn(name = "id_security")
    private Security securityId;

    public DormitorySecurityId() {

    }
}
