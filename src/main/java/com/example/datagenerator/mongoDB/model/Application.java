package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private ApplicationStatus applicationStatus;
    private ApplicationType applicationType;
    @DBRef
    private OfficeWorker officeWorker;
}