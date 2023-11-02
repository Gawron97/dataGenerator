package com.example.datagenerator.entity;

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
    @OneToOne
    @JoinColumn(name = "id_application_status")
    private ApplicationStatus applicationStatus;
    @OneToOne
    @JoinColumn(name = "id_application_type")
    private ApplicationType applicationType;
    @OneToOne
    @JoinColumn(name = "id_office_worker")
    private OfficeWorker officeWorker;
    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;
}
