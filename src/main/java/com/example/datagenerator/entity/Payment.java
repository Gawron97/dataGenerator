package com.example.datagenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate creationDate;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "id_payment_status")
    private PaymentStatus paymentStatus;
}
