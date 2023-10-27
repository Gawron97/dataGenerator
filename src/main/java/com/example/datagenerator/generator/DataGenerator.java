package com.example.datagenerator.generator;

import com.example.datagenerator.entity.Manager;
import com.example.datagenerator.entity.Student;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.repository.ManagerRepository;
import com.example.datagenerator.repository.StudentRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataGenerator {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final Faker faker = new Faker();

    public void generateUsers(int numberOfUsers) {

        for (int i = 0; i < numberOfUsers; i++) {

            Date fakerRegistrationDate = faker.date().between(new Date(2010-1900, 01, 01),
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

        if(unsignedUsers.size() >= numberOfManagers) {
            for (int i = 0; i < numberOfManagers; i++) {

                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                Manager manager = Manager.builder()
                        .seniority(Long.valueOf(faker.number().numberBetween(0,
                                2023 - chosenUser.getRegistrationDate().getYear() + 1)))
                        .user(chosenUser)
                        .build();

                managerRepository.save(manager);

            }
        }

    }

    public void generateStudents(int numberOfStudent) {

        List<User> unsignedUsers = getUnsignedUsers();
        if(unsignedUsers.size() >= numberOfStudent) {
            for (int i = 0; i < numberOfStudent; i++) {

                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                Student student = Student.builder()
                        .academicYear(faker.number().numberBetween(1,
                                Integer.min(4, 2023 - chosenUser.getRegistrationDate().getYear() + 2)))
                        .domicile(faker.address().cityName())
                        .street(faker.number().numberBetween(0, 4) == 1 ? null : faker.address().streetName())
                        .houseNumber(getHouseNumber())
                        .apartmentNumber(faker.number().numberBetween(0, 4) == 1 ? String.valueOf(faker.number().numberBetween(0, 40)) : null)
                        .zipCode(getZipCode())
                        .user(chosenUser)
                        .build();

                studentRepository.save(student);
            }
        }

    }

    private String getZipCode() {
        String zipCode = String.valueOf(faker.number().numberBetween(10, 100));
        zipCode += "-";
        zipCode += String.valueOf(faker.number().numberBetween(100, 1000));
        return zipCode;
    }

    private String getHouseNumber() {
        int probability = faker.number().numberBetween(0, 10);
        String houseNumber = String.valueOf(faker.number().numberBetween(1, 150));
        if(probability == 3) {
            houseNumber += "A";
        } else if(probability == 7) {
            houseNumber += "B";
        }
        return houseNumber;
    }

    private List<User> getUnsignedUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getManager() == null && user.getStudent() == null)
                .collect(Collectors.toList());
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
