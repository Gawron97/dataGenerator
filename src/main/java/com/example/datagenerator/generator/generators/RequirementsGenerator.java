package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Requirements;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.RequirementsRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class RequirementsGenerator extends GeneratorHelper {
    private final RequirementsRepository requirementsRepository;
    private final List<String> requirements = List.of(
            "Laureat olimpiady matematycznej, fizycznej lub informatycznej.",
            "Miejsce zameldowania powyżej 100 km od siedziby uczelni.",
            "Miejsce zameldowania powyżej 400 km od siedziby uczelni.",
            "Student konkretnego wydziału Informatyki i Telefomunikacji",
            "Osoba z niepełnosprawnością, wymagająca dostosowanych warunków zakwaterowania.",
            "Uczestnik programu wymiany międzynarodowej.",
            "Członek organizacji studenckiej zrzeszającej mieszkańców akademika.",
            "Absolwent szkoły partnerskiej uczelni, mający potwierdzone wyniki matury.",
            "Rodzina wielodzietna z dziećmi, które również studiują na uczelni.",
            "Pracownik uczelni lub pracownik administracyjny."
    );    public RequirementsGenerator(Faker faker, UserRepository userRepository, RequirementsRepository requirementsRepository) {
        super(faker, userRepository);
        this.requirementsRepository = requirementsRepository;
    }

    public void generateRequirements() {
        requirements.forEach(requirement -> requirementsRepository.save(createRequirement(requirement)));
    }

    private Requirements createRequirement(String requirement) {
        return Requirements.builder()
                .criterion(requirement)
                .build();
    }
}
