package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private String id;
    private String description;
    private LocalDate creationDate;
    private BigDecimal price;
//    @ManyToOne
//    @JoinColumn(name = "id_student")
//    private Student student;
//    @ManyToOne
//    @JoinColumn(name = "id_payment_status")
//    private PaymentStatus paymentStatus;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "contract_payments",
//            joinColumns = @JoinColumn(name="id_payment"),
//            inverseJoinColumns = @JoinColumn(name="id_contract"))
//
//    private Set<Contract> contracts = new HashSet<>();
}
