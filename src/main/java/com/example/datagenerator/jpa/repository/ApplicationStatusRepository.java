package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {

    ApplicationStatus findByStatus(String status);

}
