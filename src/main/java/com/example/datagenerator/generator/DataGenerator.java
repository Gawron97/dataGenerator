package com.example.datagenerator.generator;

import com.example.datagenerator.entity.Manager;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.repository.ManagerRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataGenerator {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final Faker faker = new Faker();

    public void generateUsers(int numberOfUsers) {

        for (int i = 0; i < numberOfUsers; i++) {

            Date fakerRegistrationDate = faker.date().between(new Date(2015-1900, 01, 01),
                    new Date(2023-1900, 10, 26));
            Date fakerLastLoginDate = faker.date().between(fakerRegistrationDate,
                    new Date(2023-1900, 10, 30));
            LocalDate registrationDate = fakerRegistrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate lastLoginDate = fakerLastLoginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            User user = User.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .registrationDate(registrationDate)
                    .lastLogin(lastLoginDate)
                    .isEnabled(faker.bool().bool())
                    .contactNumber(getPhoneNumber())
                    .build();

            userRepository.save(user);
        }

    }

    public void generateManagers(int numberOfManagers) {

        List<User> unsignedUsers = getUnsignedUsers();
        int min = 0;
        int max = unsignedUsers.size();

        for(int i=0; i<numberOfManagers; i++) {

            Manager manager = Manager.builder()
                    .seniority(Long.valueOf(faker.number().numberBetween(0, 13)))
                    .user(unsignedUsers.get(faker.number().numberBetween(min, max)))
                    .build();

            managerRepository.save(manager);

        }

    }

    private List<User> getUnsignedUsers() {
        return userRepository.findAll().stream().filter(user -> user.getManager() == null).toList();
        //TODO rozszerzyc o kolejnych urzytkownikow
    }

    private String getPhoneNumber() {
        StringBuilder number = new StringBuilder();
        for(int i=0; i<9; i++) {
            number.append(faker.number().numberBetween(0, 10));
        }
        return number.toString();
    }


}
