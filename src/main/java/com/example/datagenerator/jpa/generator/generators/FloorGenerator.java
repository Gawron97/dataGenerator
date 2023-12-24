package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Dormitory;
import com.example.datagenerator.jpa.entity.Floor;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.DormitoryRepository;
import com.example.datagenerator.jpa.repository.FloorRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.stream.LongStream;

public class FloorGenerator extends GeneratorHelper {

    private final FloorRepository floorRepository;
    private final DormitoryRepository dormitoryRepository;
    private final List<String> descriptions = List.of(
            "Przestronna przestrzeń z widokiem na kampus!",
            "Pokój z przytulnym dywanem i kolorowymi poduszkami.",
            "Minimalistyczny design sprzyjający nauce i relaksowi.",
            "Atmosfera sprzyjająca kreatywności i wymianie pomysłów.",
            "Nowoczesne wyposażenie i szybki dostęp do internetu.",
            "Pokój z ekologicznymi rozwiązaniami i naturalnym oświetleniem.",
            "Klimatyzacja dla komfortu w gorące dni.",
            "Przemyślana aranżacja, idealna do nauki i odpoczynku.",
            "Energia pozytywna w każdym kącie!",
            "Wygodne łóżko zapewniające regenerację po intensywnym dniu.",
            "Pokój z motywem roślinnym dla miłośników natury.",
            "Kącik do pracy zdalnej z ergonomicznym biurkiem.");

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
                    .level(level)
                    .description(descriptions.get(faker.number().numberBetween(0, descriptions.size())))
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
