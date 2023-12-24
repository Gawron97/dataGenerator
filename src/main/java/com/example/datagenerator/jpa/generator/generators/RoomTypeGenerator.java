package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.RoomType;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.RoomTypeRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class RoomTypeGenerator extends GeneratorHelper {

    private final RoomTypeRepository roomTypeRepository;
    private static final List<String> types = List.of("Standardowy", "Dla niepełnosprawnych", "Rodzinny", "Dla obcokrajowców");

    public RoomTypeGenerator(Faker faker, UserRepository userRepository, RoomTypeRepository roomTypeRepository) {
        super(faker, userRepository);
        this.roomTypeRepository = roomTypeRepository;
    }

    public void generateRoomTypes() {
        types.forEach(type -> roomTypeRepository.save(createRoomType(type)));
    }

    private RoomType createRoomType(String type) {
        return RoomType.builder()
                .type(type)
                .build();
    }
}
