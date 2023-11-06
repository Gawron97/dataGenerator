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
public class ContractPayments {
    @Id
    private Long id_payment;

    @Id
    private Long id_contract;

    @ManyToOne
    @JoinColumn(name = "id_payment", insertable = false, updatable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_contract", insertable = false, updatable = false)
    private Contract contract;
}

//idk czy to tak ma działać xd pewnie jest źle