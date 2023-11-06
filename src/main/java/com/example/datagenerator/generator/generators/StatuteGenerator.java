package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Statute;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.AdditionalRoomTypeRepository;
import com.example.datagenerator.repository.StatuteRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class StatuteGenerator extends GeneratorHelper {

    private final StatuteRepository statuteRepository;

    private static final List<String> contents = List.of("Ustawa_1", "Ustawa_2", "Ustawa_3", "Ustawa_4");

    public StatuteGenerator(Faker faker, UserRepository userRepository, StatuteRepository statuteRepository) {
        super(faker, userRepository);
        this.statuteRepository = statuteRepository;
    }

    public void generateStatutes(){
        contents.forEach( content -> statuteRepository.save(createStatute(content)));
    }

    private Statute createStatute(String content){

        return Statute.builder().content(content).additional_info(null).build();

    }


}
