package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.OfficeWorker;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.OfficeWorkerRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class OfficeWorkerGenerator extends GeneratorHelper {
    private final OfficeWorkerRepository officeWorkerRepository;

    public OfficeWorkerGenerator(Faker faker, OfficeWorkerRepository officeWorkerRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.officeWorkerRepository = officeWorkerRepository;
    }

    public void generateOfficeWorker(int numberOfOfficeWorkers) {
//        List<User> unsignedUsers = getUnsignedUsers();
//
//        if (unsignedUsers.size() < numberOfOfficeWorkers) {
//            return;
//        }
//        for (int i = 0; i < numberOfOfficeWorkers; i++) {
//            User chosenUser = getRandomUserFromList(unsignedUsers);
//
//            if (chosenUser != null) {
//                OfficeWorker officeWorker = generateOfficeWorkerForUser(chosenUser);
//                officeWorkerRepository.save(officeWorker);
//            }
//        }
    }

    private OfficeWorker generateOfficeWorkerForUser(User user) {
        return OfficeWorker.builder()
                .seniority(getSeniority(user.getRegistrationDate()))
                .user(user)
                .build();
    }
}
