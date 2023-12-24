package com.example.datagenerator.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate submissionDate;
    private String preferredLocation;
    private String additionalInformation;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "id_application_status")
    private ApplicationStatus applicationStatus;
    @ManyToOne
    @JoinColumn(name = "id_application_type")
    private ApplicationType applicationType;
    @ManyToOne
    @JoinColumn(name = "id_office_worker")
    private OfficeWorker officeWorker;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
}