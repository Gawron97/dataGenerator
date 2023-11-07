package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.Floor;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.FloorRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.LongStream;

public class FloorGenerator extends GeneratorHelper {

    private final FloorRepository floorRepository;
    private final DormitoryRepository dormitoryRepository;

    public FloorGenerator(Faker faker, UserRepository userRepository, FloorRepository floorRepository, DormitoryRepository dormitoryRepository){
        super(faker, userRepository);
        this.floorRepository = floorRepository;
        this.dormitoryRepository = dormitoryRepository;

    }

    public void generateFloors(){

        List<Dormitory> dorms = dormitoryRepository.findAll();

        dorms.forEach(dorm -> generateFloorsForDormitory(dorm));

    }

    private void generateFloorsForDormitory(Dormitory dormitory){

        LongStream levels = getLevelsRange();

        levels.forEach( level -> {
            Floor currentFloor = Floor.builder()
                    .level(level).description(null)
                    .isAvailable(super.getFieldFillingProbability(85))
                    .dormitory(dormitory)
                    .build();

            floorRepository.save(currentFloor);
        });

    }

    private LongStream getLevelsRange(){
        int lowestLevel = faker.number().numberBetween(-1,1);
        int highestLevel = faker.number().numberBetween(5,13);

        return LongStream.range(lowestLevel,highestLevel);

    }



}
