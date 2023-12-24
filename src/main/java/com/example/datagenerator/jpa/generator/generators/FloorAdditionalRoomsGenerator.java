package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.AdditionalRooms;
import com.example.datagenerator.jpa.entity.Floor;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.AdditionalRoomsRepository;
import com.example.datagenerator.jpa.repository.FloorRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FloorAdditionalRoomsGenerator extends GeneratorHelper {

    private final List<Floor> floors;
    private final List<AdditionalRooms> additionalRooms;
    private final FloorRepository floorRepository;

    public FloorAdditionalRoomsGenerator(Faker faker, UserRepository userRepository, AdditionalRoomsRepository additionalRoomsRepository, FloorRepository floorRepository) {
        super(faker, userRepository);
        this.floors = floorRepository.findAll();
        this.additionalRooms = additionalRoomsRepository.findAll();
        this.floorRepository = floorRepository;
    }

    @Transactional
    public void generateFloorAdditionalRooms() {

        floors.forEach(floor -> {

            Set<AdditionalRooms> rooms = getRandomRooms(faker.number().numberBetween(0, 6));
            rooms.forEach(room -> {
                floor.getAdditionalRooms().add(room);

            });
            floorRepository.save(floor);

        });

    }

    private Set<AdditionalRooms> getRandomRooms(int number) {
        Set<AdditionalRooms> rooms = new HashSet<>();
        for (int i = 0; i < number; i++) {
            rooms.add(this.additionalRooms.get(faker.number().numberBetween(0, this.additionalRooms.size())));
        }
        return rooms;
    }


}
