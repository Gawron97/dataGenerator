package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.User;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

public class UserGenerator extends GeneratorHelper {
    private final UserRepository userRepository;
    public UserGenerator(Faker faker, UserRepository userRepository) {
        super(faker, userRepository);
        this.userRepository = userRepository;
    }

    public void generateUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = generateRandomUser();
            userRepository.save(user);
        }
    }

}
