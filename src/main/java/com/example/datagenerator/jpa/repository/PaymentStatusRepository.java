package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
}
