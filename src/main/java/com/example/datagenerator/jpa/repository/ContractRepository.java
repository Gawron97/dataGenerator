package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
