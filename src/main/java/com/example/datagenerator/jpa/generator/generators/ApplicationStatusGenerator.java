package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.ApplicationStatus;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ApplicationStatusRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ApplicationStatusGenerator extends GeneratorHelper {
    private final ApplicationStatusRepository applicationStatusRepository;
    private final List<String> statuses = List.of("Zlozona", "Zaakceptowana", "Odrzucona", "Cofnieta", "Rozpatrywana");

    public ApplicationStatusGenerator(Faker faker, UserRepository userRepository, ApplicationStatusRepository applicationStatusRepository) {
        super(faker, userRepository);
        this.applicationStatusRepository = applicationStatusRepository;
    }

    public void generateApplicationStatuses() {
        statuses.forEach(type -> applicationStatusRepository.save(createApplicationStatus(type)));
    }

    private ApplicationStatus createApplicationStatus(String type) {
        return ApplicationStatus.builder()
                .status(type)
                .build();
    }
}
