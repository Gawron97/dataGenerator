package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Faculty;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.FacultyRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class FacultyGenerator extends GeneratorHelper {

    private final FacultyRepository facultyRepository;
    private final List<String> faculties = List.of("Wydzial Informatyki i Telekomunikacji", "Wydzial Zarzadzania", "Wydzial Chemiczny", "Wydzial Budowniczy", "Wydzial Elektryczny", "Wydzial Medyczny", "Wydzial Mechaniczny");

    public FacultyGenerator(Faker faker, UserRepository userRepository, FacultyRepository facultyRepository) {
        super(faker, userRepository);
        this.facultyRepository = facultyRepository;
    }

    public void generateFaculties() {

        for (String s : faculties) {
            Faculty faculty = Faculty.builder()
                    .name(s)
                    .deanOfficeLocation(getFieldFillingProbability(75) ? faker.address().streetName() : null)
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .emailAddress(getFieldFillingProbability(75) ? faker.internet().emailAddress() : null)
                    .build();

            facultyRepository.save(faculty);
        }

    }

}
