package com.example.datagenerator.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class DormitorySecurity {
    private Long idDormitory;
    private Long idSecurity;
    private Long seniority;
    private String salary;

}
