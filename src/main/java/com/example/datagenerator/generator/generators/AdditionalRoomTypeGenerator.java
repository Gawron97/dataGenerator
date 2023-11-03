package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.AdditionalRoomType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.AdditionalRoomTypeRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class AdditionalRoomTypeGenerator extends GeneratorHelper {

    private final AdditionalRoomTypeRepository additionalRoomTypeRepository;
    private static final List<String> types = List.of("Lazienka", "Sala gier", "Salon", "Pokoj administracyjny",
            "Kuchnia", "Pralnia", "Sala nauki", "Stolowka", "Biblioteka", "Pokoj na rowery");

    public AdditionalRoomTypeGenerator(Faker faker, UserRepository userRepository, AdditionalRoomTypeRepository additionalRoomTypeRepository) {
        super(faker, userRepository);
        this.additionalRoomTypeRepository = additionalRoomTypeRepository;
    }

    public void generateAdditionalRoomTypes() {
        types.forEach(type -> additionalRoomTypeRepository.save(createAdditionalRoomType(type)));
    }

    private AdditionalRoomType createAdditionalRoomType(String type) {
        return AdditionalRoomType.builder()
                .type(type)
                .build();
    }

}