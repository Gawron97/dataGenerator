package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Application;
import com.example.datagenerator.entity.Student;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ApplicationRepository;
import com.example.datagenerator.repository.StudentRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class ApplicationGenerator extends GeneratorHelper {
    private final ApplicationRepository applicationRepository;
    private final List<Student> students;
    public ApplicationGenerator(Faker faker, UserRepository userRepository, ApplicationRepository applicationRepository,
                                StudentRepository studentRepository) {
        super(faker, userRepository);
        this.applicationRepository = applicationRepository;
        students = studentRepository.findAll();
    }

    public void generateApplications(int numberOfApplications) {
        for (int i = 0; i < numberOfApplications; i++) {
            Student chosenStudent = getRandomStudent();

//            Application application = Application.builder()
//                    .submissionDate(getRandomDateLaterThen(chosenStudent.getUser().getRegistrationDate()))
//                    .preferredLocation("Idk what is location")
//                    .additionalInformation("Empty for now")
//                    .student(chosenStudent)
//                    .officeWorker()
//                    .applicationType()
//                    .applicationStatus()
//                    .build();
//            applicationRepository.save(application);
        }
    }

    private Student getRandomStudent() {
        return students.get(faker.number().numberBetween(0, students.size()));
    }
}
