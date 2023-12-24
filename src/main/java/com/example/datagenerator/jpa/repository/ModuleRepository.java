package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}