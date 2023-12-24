package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}
