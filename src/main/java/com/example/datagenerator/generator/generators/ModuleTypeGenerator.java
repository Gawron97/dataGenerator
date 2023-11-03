package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.ModuleType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ModuleTypeRepository;
import com.example.datagenerator.repository.UserRepository;
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
