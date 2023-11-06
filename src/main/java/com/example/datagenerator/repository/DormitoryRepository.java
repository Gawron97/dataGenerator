package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Complain;
import com.example.datagenerator.entity.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {
}
