package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
