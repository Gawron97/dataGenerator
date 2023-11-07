package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
