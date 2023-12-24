package com.example.datagenerator.jpa.entity;


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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate creationDate;
    private LocalDate paymentDue;
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;
}
