package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.AdditionalRooms;
import com.example.datagenerator.entity.AdditionalRoomType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.AdditionalRoomsRepository;
import com.example.datagenerator.repository.AdditionalRoomTypeRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class AdditionalRoomsGenerator extends GeneratorHelper {

    private final AdditionalRoomsRepository additionalRoomsRepository;
    private final AdditionalRoomTypeRepository additionalRoomTypeRepository;

    public AdditionalRoomsGenerator(Faker faker, UserRepository userRepository, AdditionalRoomsRepository additionalRoomsRepository, AdditionalRoomTypeRepository additionalRoomTypeRepository) {
        super(faker, userRepository);
        this.additionalRoomsRepository = additionalRoomsRepository;
        this.additionalRoomTypeRepository = additionalRoomTypeRepository;
    }

    public void generateAdditionalRooms(int numberOfAdditionalRooms) {

        List<AdditionalRoomType> additionalRoomTypes = getAdditionalRoomTypes();
        if(additionalRoomTypes.isEmpty()) {
            return;
        }

        for (int i = 0; i < numberOfAdditionalRooms; i++) {
            AdditionalRooms additionalRooms = AdditionalRooms.builder()
                    .roomNumber(faker.number().numberBetween(1, 110))
                    .description(null)
                    .additionalRoomType(getRandomAdditionalRoomType(additionalRoomTypes))
                    .build();

            additionalRoomsRepository.save(additionalRooms);
        }
    }

    private List<AdditionalRoomType> getAdditionalRoomTypes() {
        return additionalRoomTypeRepository.findAll();
    }

    private AdditionalRoomType getRandomAdditionalRoomType(List<AdditionalRoomType> additionalRoomTypes) {
        return additionalRoomTypes.get(faker.number().numberBetween(0, additionalRoomTypes.size()));
    }

}
