package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.AdditionalServices;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.AdditionalServicesRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DormitoryServicesGenerator extends GeneratorHelper {

    private final List<Dormitory> dormitories;
    private final List<AdditionalServices> services;
    private final DormitoryRepository dormitoryRepository;

    public DormitoryServicesGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository, AdditionalServicesRepository additionalServicesRepository) {
        super(faker, userRepository);
        dormitories = dormitoryRepository.findAll();
        services = additionalServicesRepository.findAll();
        this.dormitoryRepository = dormitoryRepository;
    }

    @Transactional
    public void generateDormitoryServices() {

        dormitories.forEach(dormitory -> {

            Set<AdditionalServices> services = getRandomServices(faker.number().numberBetween(0, 6));

            services.forEach(service -> {
                dormitory.getAdditionalServices().add(service);
            });

            dormitoryRepository.save(dormitory);
        });

    }

    private Set<AdditionalServices> getRandomServices(int number) {
        Set<AdditionalServices> services = new HashSet<>();
        for (int i = 0; i < number; i++) {
            services.add(this.services.get(faker.number().numberBetween(0, this.services.size())));
        }
        return services;
    }


}
