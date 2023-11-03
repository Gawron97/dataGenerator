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
            System.out.println(user);
//            userRepository.save(user);
        }
    }

    private User generateRandomUser() {

        LocalDate registrationDate = getRandomDateBetween(LocalDate.of(2010, 1, 11),
                LocalDate.of(2023, 9, 26));
        LocalDate lastLoginDate = getRandomDateLaterThen(registrationDate);

        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .registrationDate(registrationDate)
                .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                .isEnabled(faker.bool().bool())
                .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                .build();
    }

}
