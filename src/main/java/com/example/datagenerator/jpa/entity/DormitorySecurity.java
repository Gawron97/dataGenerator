package com.example.datagenerator.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DormitorySecurity {

    @EmbeddedId
    private DormitorySecurityId id;
    private Long seniority;
    private Integer salary;

}