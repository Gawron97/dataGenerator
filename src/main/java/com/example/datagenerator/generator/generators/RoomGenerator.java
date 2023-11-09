package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Floor;
import com.example.datagenerator.entity.Module;
import com.example.datagenerator.entity.ModuleType;
import com.example.datagenerator.entity.Room;
import com.example.datagenerator.entity.RoomType;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.List;

public class RoomGenerator extends GeneratorHelper {

    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;

    private final List<RoomType> roomTypes;
    private final List<Module> modules;

    public RoomGenerator(Faker faker, UserRepository userRepository,RoomRepository roomRepository,
                         FloorRepository floorRepository, RoomTypeRepository roomTypeRepository, ModuleRepository moduleRepository){
        super(faker, userRepository);
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.roomTypes = roomTypeRepository.findAll();
        this.modules = moduleRepository.findAll();
    }


    public void generateRooms(int minNumberOfRoomsPerFloor, int maxNumberOfRoomsPerFloor) {

        List<Floor> floors = getFloorsForRent();

        floors.forEach(floor -> {

            int numberOfRooms = super.faker.number().numberBetween(minNumberOfRoomsPerFloor, maxNumberOfRoomsPerFloor);
            generateSeparateRooms(numberOfRooms, floor);

        });


    }

    private void generateSeparateRooms(int numberOfRooms, Floor floor){

        for(int i = 1; i <= numberOfRooms; i++){

            String number = floor.getLevel()+String.format("%02d", i);
            Long size = getRandomSize();
            Long numberOfBeds = getRandomNumberOfBeds();

            Room currentRoom = Room.builder()
                    .roomNumber(number)
                    .description(faker.lorem().word())
                    .numberOfBeds(numberOfBeds)
                    .has_a_bathroom(getFieldFillingProbability(80))
                    .price(BigDecimal.valueOf(size * 40))
                    .size(BigDecimal.valueOf(size))
                    .numberOfBeds(numberOfBeds)
                    .isAvailable(faker.bool().bool())
                    .floor(floor)
                    .module(modules.get(faker.number().numberBetween(0, modules.size())))
                    .roomType(getRoomType())
                    .build();

            roomRepository.save(currentRoom);

        }


    }

    private void generateRoomsInModules() {
        // to generate module and rooms assigned to it on the same time

    }

    private List<Floor> getFloorsForRent() {

        return floorRepository.findByLevelGreaterThanEqual((long) 1);
    }

    private RoomType getRoomType() {
        return roomTypes.get(faker.number().numberBetween(0, roomTypes.size()));
    }

    private Long getRandomNumberOfBeds() {
        return (long) faker.number().numberBetween(1, 5);
    }

    private Long getRandomSize() {
        return (long) faker.number().numberBetween(10, 21);
    }


}
