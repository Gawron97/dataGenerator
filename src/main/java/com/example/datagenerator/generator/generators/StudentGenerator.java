package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Student;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.StudentRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class StudentGenerator extends GeneratorHelper {
    private final StudentRepository studentRepository;

    public StudentGenerator(Faker faker, StudentRepository studentRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.studentRepository = studentRepository;
    }

    public void generateStudents(int numberOfStudents) {

        for (int i = 0; i < numberOfStudents; i++) {
            User chosenUser = generateRandomUser();
            Student student = generateStudentForUser(chosenUser);
            studentRepository.save(student);
        }

    }

    private Student generateStudentForUser(User user) {
        int maxAcademicYear = Integer.min(4, 2023 - user.getRegistrationDate().getYear() + 2);

        return Student.builder()
                .academicYear((long) faker.number().numberBetween(1, maxAcademicYear))
                .domicile(getFieldFillingProbability(75) ? faker.address().cityName() : null)
                .street(getFieldFillingProbability(60) ? faker.address().streetName() : null)
                .houseNumber(getFieldFillingProbability(75) ? getHouseNumber() : null)
                .apartmentNumber(getFieldFillingProbability(25) ? String.valueOf(faker.number().numberBetween(0, 40)) : null)
                .zipCode(getFieldFillingProbability(75) ? getZipCode() : null)
                .user(user)
                .build();
    }
}
