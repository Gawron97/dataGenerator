package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT c.student.id FROM Contract c WHERE c.start_date <= :endDate AND c.end_date >= :startDate)")
    List<Student> findStudentsWithoutContractsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
