package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Complain;
import com.example.datagenerator.entity.Student;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ComplainRepository;
import com.example.datagenerator.repository.StudentRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;

public class ComplainGenerator extends GeneratorHelper {

    private final List<String> titles = List.of("Zaniedbanie", "Zgloszenie", "Naduzycie", "Halas");
    private final ComplainRepository complainRepository;
    private final List<Student> students;

    public ComplainGenerator(Faker faker, UserRepository userRepository, ComplainRepository complainRepository,
                             StudentRepository studentRepository) {
        super(faker, userRepository);
        this.complainRepository = complainRepository;
        students = studentRepository.findAll();
    }

    public void generateComplains(int numberOfComplains) {

        for (int i = 0; i < numberOfComplains; i++) {

            Student chosenStudent = getRandomStudent();

            Complain complain = Complain.builder()
                    .submissionDate(getRandomDateLaterThen(chosenStudent.getUser().getRegistrationDate()))
                    .author(getFieldFillingProbability(75) ? faker.funnyName().name() : null)
                    .title(titles.get(faker.number().numberBetween(0, titles.size())))
                    .description(null)
                    .student(chosenStudent)
                    .build();

            complainRepository.save(complain);
        }
    }

    private Student getRandomStudent() {
        return students.get(faker.number().numberBetween(0, students.size()));
    }

}
