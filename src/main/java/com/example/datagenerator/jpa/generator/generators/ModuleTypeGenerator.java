package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.ModuleType;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ModuleTypeRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ModuleTypeGenerator extends GeneratorHelper {

    private final ModuleTypeRepository moduleTypeRepository;
    private static final List<String> types = List.of("Rodzinny", "Dla niepelnosprawnych", "Dla obcokrajowcow",
            "Standardowy");

    public ModuleTypeGenerator(Faker faker, UserRepository userRepository, ModuleTypeRepository moduleTypeRepository) {
        super(faker, userRepository);
        this.moduleTypeRepository = moduleTypeRepository;
    }

    public void generateModuleTypes() {
        types.forEach(type -> moduleTypeRepository.save(createModuleType(type)));
    }

    private ModuleType createModuleType(String type) {
        return ModuleType.builder()
                .type(type)
                .build();
    }

}
