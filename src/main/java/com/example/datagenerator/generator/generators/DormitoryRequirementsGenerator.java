package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.Requirements;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.RequirementsRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DormitoryRequirementsGenerator extends GeneratorHelper {

    private final List<Dormitory> dormitories;
    private final List<Requirements> requirements;
    private final DormitoryRepository dormitoryRepository;

    public DormitoryRequirementsGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository, RequirementsRepository requirementsRepository) {
        super(faker, userRepository);
        dormitories = dormitoryRepository.findAll();
        requirements = requirementsRepository.findAll();
        this.dormitoryRepository = dormitoryRepository;
    }

    @Transactional
    public void generateDormitoryRequirements() {

        dormitories.forEach(dormitory -> {

            Set<Requirements> requirements = getRandomRequirements(5);

            requirements.forEach(requirement -> {
                dormitory.getRequirements().add(requirement);
            });

            dormitoryRepository.save(dormitory);
        });

    }

    private Set<Requirements> getRandomRequirements(int number) {
        Set<Requirements> requirements = new HashSet<>();
        for (int i = 0; i < number; i++) {
            requirements.add(this.requirements.get(faker.number().numberBetween(0, this.requirements.size())));
        }
        return requirements;
    }


}
