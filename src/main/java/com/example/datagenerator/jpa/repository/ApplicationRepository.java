package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
