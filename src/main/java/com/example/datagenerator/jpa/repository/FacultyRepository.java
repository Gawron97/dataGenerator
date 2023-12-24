package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
