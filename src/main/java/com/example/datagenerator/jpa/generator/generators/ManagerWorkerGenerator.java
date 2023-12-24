package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Manager;
import com.example.datagenerator.jpa.entity.User;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ManagerRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

public class ManagerWorkerGenerator extends GeneratorHelper {
    private final ManagerRepository managerRepository;

    public ManagerWorkerGenerator(Faker faker, ManagerRepository managerRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.managerRepository = managerRepository;
    }

    public void generateManagers(int numberOfManagers) {
        for (int i = 0; i < numberOfManagers; i++) {
            User user = generateRandomUser();
            Manager manager = generateManagerForUser(user);
            managerRepository.save(manager);
        }
    }

    private Manager generateManagerForUser(User user) {
        return Manager.builder()
                .workSchedule(getFieldFillingProbability(75) ? getWorkSchedule() : null)
                .seniority(getSeniority(user.getRegistrationDate()))
                .user(user)
                .build();
    }

}
