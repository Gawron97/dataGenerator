package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Faculty;
import com.example.datagenerator.entity.FieldOfStudy;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.FacultyRepository;
import com.example.datagenerator.repository.FieldOfStudyRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOfStudyGenerator extends GeneratorHelper {
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final FacultyRepository facultyRepository;
    private final Map<String, List<String>> facultyPrograms = new HashMap<>();
    private final List<String> levels = List.of("I stopnia", "II stopnia");

    public FieldOfStudyGenerator(Faker faker, UserRepository userRepository, FieldOfStudyRepository fieldOfStudyRepository, FacultyRepository facultyRepository) {
        super(faker, userRepository);
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.facultyRepository = facultyRepository;

        facultyPrograms.put("Wydzial Informatyki i Telekomunikacji", List.of(
                "Informatyka stosowana",
                "Informatyka algorytmiczna",
                "Teleinformatyka",
                "Sieci komputerowe",
                "Inżynieria oprogramowania",
                "Cyberbezpieczeństwo",
                "Sztuczna inteligencja i systemy ekspertowe"
        ));

        facultyPrograms.put("Wydzial Zarzadzania", List.of(
                "Zarządzanie",
                "Zarządzanie projektami",
                "Logistyka",
                "Ekonomia",
                "Inżynieria jakości",
                "Zarządzanie zasobami ludzkimi"
        ));

        facultyPrograms.put("Wydzial Chemiczny", List.of(
                "Chemia",
                "Inżynieria chemiczna",
                "Technologia chemiczna",
                "Chemia analityczna",
                "Biotechnologia",
                "Chemia medyczna"
        ));

        facultyPrograms.put("Wydzial Budowniczy", List.of(
                "Budownictwo",
                "Inżynieria lądowa",
                "Budownictwo ogólnego użytku",
                "Inżynieria środowiska",
                "Zarządzanie budową",
                "Inżynieria sanitarystyczna"
        ));

        facultyPrograms.put("Wydzial Elektryczny", List.of(
                "Elektrotechnika",
                "Automatyka i robotyka",
                "Informatyka i elektrotechnika",
                "Energetyka",
                "Telekomunikacja",
                "Elektronika"
        ));

        facultyPrograms.put("Wydzial Medyczny", List.of(
                "Medycyna",
                "Stomatologia",
                "Farmacja",
                "Pielęgniarstwo",
                "Laboratoryjna diagnostyka medyczna",
                "Fizjoterapia"
        ));

        facultyPrograms.put("Wydzial Mechaniczny", List.of(
                "Mechanika i budowa maszyn",
                "Transport",
                "Inżynieria produkcji",
                "Mechanika i technika pojazdów",
                "Inżynieria lotnicza",
                "Inżynieria w biomedycynie"
        ));
    }

    public void generateFieldsOfStudy() {
        facultyPrograms.forEach((facultyName, programs) -> {
            programs.forEach(program -> {
                if (facultyRepository.findAll().stream().anyMatch(f -> f.getName().equals(facultyName))) {
                    var faculty = facultyRepository.findAll().stream().filter(f -> f.getName().equals(facultyName)).findFirst().get();
                    fieldOfStudyRepository.save(createFieldOfStudy(program, levels.get(faker.number().numberBetween(0, levels.size())), faculty));

                }
            });
        });
    }

    private FieldOfStudy createFieldOfStudy(String name, String level, Faculty faculty) {
        return FieldOfStudy.builder()
                .name(name)
                .level(level)
                .faculty(faculty)
                .build();
    }
}
