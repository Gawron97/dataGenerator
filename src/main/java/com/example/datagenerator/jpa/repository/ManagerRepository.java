package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
