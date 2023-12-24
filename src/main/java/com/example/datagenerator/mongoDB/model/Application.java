package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private String id;
    private LocalDate submissionDate;
    private String preferredLocation;
    private String additionalInformation;
    private LocalDate startDate;
    private LocalDate endDate;
//    @ManyToOne
//    @JoinColumn(name = "id_application_status")
//    private ApplicationStatus applicationStatus;
//    @ManyToOne
//    @JoinColumn(name = "id_application_type")
//    private ApplicationType applicationType;
//    @ManyToOne
//    @JoinColumn(name = "id_office_worker")
//    private OfficeWorker officeWorker;
//    @ManyToOne
//    @JoinColumn(name = "id_student")
//    private Student student;
}