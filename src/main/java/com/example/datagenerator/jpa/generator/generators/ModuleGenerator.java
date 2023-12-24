package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Module;
import com.example.datagenerator.jpa.entity.ModuleType;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ModuleRepository;
import com.example.datagenerator.jpa.repository.ModuleTypeRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ModuleGenerator extends GeneratorHelper {

    private final ModuleRepository moduleRepository;
    private final List<ModuleType> moduleTypes;

    public ModuleGenerator(Faker faker, UserRepository userRepository, ModuleRepository moduleRepository, ModuleTypeRepository moduleTypeRepository) {
        super(faker, userRepository);
        this.moduleRepository = moduleRepository;
        moduleTypes = moduleTypeRepository.findAll();
    }

    public void generateModules (int numberOfModules) {

        if(moduleTypes.isEmpty()) {
            return;
        }

        for (int i = 0; i < numberOfModules; i++) {
            Module module = Module.builder()
                    .has_a_bathroom(faker.bool().bool())
                    .moduleType(getRandomModuleType())
                    .build();

            moduleRepository.save(module);
        }
    }

    private ModuleType getRandomModuleType() {
        return moduleTypes.get(faker.number().numberBetween(0, moduleTypes.size()));
    }

}
