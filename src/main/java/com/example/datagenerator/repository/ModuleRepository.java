package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}