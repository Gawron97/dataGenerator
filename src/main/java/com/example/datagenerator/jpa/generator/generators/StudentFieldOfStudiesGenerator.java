package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.FieldOfStudy;
import com.example.datagenerator.jpa.entity.Student;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.StudentRepository;
import com.example.datagenerator.jpa.repository.FieldOfStudyRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentFieldOfStudiesGenerator extends GeneratorHelper {

    private final List<Student> students;
    private final List<FieldOfStudy> fieldOfStudies;
    private final StudentRepository studentRepository;

    public StudentFieldOfStudiesGenerator(Faker faker, UserRepository userRepository, StudentRepository studentRepository, FieldOfStudyRepository fieldOfStudyRepository) {
        super(faker, userRepository);
        students = studentRepository.findAll();
        fieldOfStudies = fieldOfStudyRepository.findAll();
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void generateStudentFieldOfStudies() {

        students.forEach(student -> {

            Set<FieldOfStudy> fieldOfStudies = getRandomFieldOfStudies(faker.number().numberBetween(1, 3));

            fieldOfStudies.forEach(fieldOfStudy -> {
                student.getFieldOfStudies().add(fieldOfStudy);
            });

            studentRepository.save(student);
        });

    }

    private Set<FieldOfStudy> getRandomFieldOfStudies(int number) {
        Set<FieldOfStudy> fieldOfStudies = new HashSet<>();
        for (int i = 0; i < number; i++) {
            fieldOfStudies.add(this.fieldOfStudies.get(faker.number().numberBetween(0, this.fieldOfStudies.size())));
        }
        return fieldOfStudies;
    }


}
