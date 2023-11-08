package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.Requirements;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.RequirementsRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;

import java.util.List;

public class DormitoryRequirementsGenerator extends GeneratorHelper {

    private final DormitoryRepository dormitoryRepository;
    private final RequirementsRepository requirementsRepository;

    public DormitoryRequirementsGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository, RequirementsRepository requirementsRepository) {
        super(faker, userRepository);
        this.dormitoryRepository = dormitoryRepository;
        this.requirementsRepository = requirementsRepository;
    }

    @Transactional
    public void generateDormitoryRequirements() {

        List<Dormitory> dormitories = dormitoryRepository.findAll();
        List<Requirements> requirements = requirementsRepository.findAll();

        dormitories.forEach(dormitory -> {
            dormitory.getRequirements().add(requirements.get(faker.number().numberBetween(0, requirements.size())));
            dormitoryRepository.save(dormitory);
        });

    }


}
