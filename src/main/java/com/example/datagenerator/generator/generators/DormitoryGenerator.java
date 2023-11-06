package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.Faculty;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;

import java.util.List;

public class DormitoryGenerator extends GeneratorHelper {

    private final DormitoryRepository dormitoryRepository;
    private final ManagerRepository managerRepository;
    private final StatuteRepository statuteRepository;

    private final List<String> dormitories =
            List.of("T2", "T3", "T4", "T6", "T15", "T16", "T17", "T18", "T19");

    public DormitoryGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository,
                              ManagerRepository managerRepository, StatuteRepository statuteRepository) {
        super(faker, userRepository);
        this.dormitoryRepository = dormitoryRepository;
        this.managerRepository = managerRepository;
        this.statuteRepository = statuteRepository;
    }


    public void generateDormitories() {

        for (int i = 0; i < dormitories.size(); i++) {
            Dormitory dorm = Dormitory.builder()
                    .name(dormitories.get(i))
                    .city(faker.address().city())
                    .street(faker.address().streetName()).numberOfStreet(faker.address().streetAddressNumber())
                    .description(null)
                    .contactInfo(super.getPhoneNumber()).manager(super.getRandomManager(this.managerRepository))
                    .statute(super.getRandomStatute(this.statuteRepository)).build();


            dormitoryRepository.save(dorm);
        }

    }
}
