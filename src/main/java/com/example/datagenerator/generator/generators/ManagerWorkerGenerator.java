package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Manager;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ManagerRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ManagerWorkerGenerator extends GeneratorHelper {
    private final ManagerRepository managerRepository;
    public ManagerWorkerGenerator(Faker faker, ManagerRepository managerRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.managerRepository = managerRepository;
    }

    public void generateManagers(int numberOfManagers) {
        List<User> unsignedUsers = getUnsignedUsers();

        if (unsignedUsers.size() < numberOfManagers) {
            return;
        }

        for (int i = 0; i < numberOfManagers; i++) {
            User chosenUser = getRandomUserFromList(unsignedUsers);
            if (chosenUser != null) {
                Manager manager = generateManagerForUser(chosenUser);
                managerRepository.save(manager);
            }
        }

    }
}
