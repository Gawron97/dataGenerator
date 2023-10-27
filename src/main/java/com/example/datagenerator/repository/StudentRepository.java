package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
