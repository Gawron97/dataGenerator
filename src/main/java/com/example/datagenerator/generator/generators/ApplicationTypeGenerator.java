package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.ApplicationType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ApplicationTypeRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ApplicationTypeGenerator extends GeneratorHelper {
    private final ApplicationTypeRepository applicationTypeRepository;
    private final List<String> types = List.of("Zlozona", "Zaakceptowana", "Odrzucona", "Cofnięta");
    public ApplicationTypeGenerator(Faker faker, UserRepository userRepository, ApplicationTypeRepository applicationTypeRepository) {
        super(faker, userRepository);
        this.applicationTypeRepository = applicationTypeRepository;
    }

    public void generateApplicationTypes() {
        types.forEach(type -> applicationTypeRepository.save(createApplicationType(type)));
    }

    private ApplicationType createApplicationType(String type) {
        return ApplicationType.builder()
                .type(type)
                .build();
    }
}
