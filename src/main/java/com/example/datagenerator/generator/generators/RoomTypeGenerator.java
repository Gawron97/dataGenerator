package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.RoomType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.RoomTypeRepository;
import com.example.datagenerator.repository.UserRepository;
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
