package com.example.datagenerator.generator.methods;

import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.CommonMethods;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserGenerator extends CommonMethods {
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

    private User generateRandomUser() {
        Date fakerRegistrationDate = faker.date().between(new Date(110, 0, 1), new Date(123, 9, 26));
        Date fakerLastLoginDate = faker.date().between(fakerRegistrationDate, new Date(123, 9, 30));

        LocalDate registrationDate = fakerRegistrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastLoginDate = fakerLastLoginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

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
