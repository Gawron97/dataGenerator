package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.ApplicationType;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ApplicationTypeRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ApplicationTypeGenerator extends GeneratorHelper {
    private final ApplicationTypeRepository applicationTypeRepository;
    private final List<String> types = List.of("Przyznanie miejsca", "Przedluzenie wynajmu", "Zmiana pokoju", "Zmiana akademika",
            "Przedwczesna rezygnacja z wynajmu");
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
