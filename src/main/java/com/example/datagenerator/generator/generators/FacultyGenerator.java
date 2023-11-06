package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Faculty;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.FacultyRepository;
import com.example.datagenerator.repository.UserRepository;
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

        for (int i = 0; i < faculties.size(); i++) {
            Faculty faculty = Faculty.builder()
                    .name(faculties.get(i))
                    .deanOfficeLocation(getFieldFillingProbability(75) ? faker.address().streetName() : null)
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .emailAddress(getFieldFillingProbability(75) ? faker.internet().emailAddress() : null)
                    .build();

            facultyRepository.save(faculty);
        }

    }

}
