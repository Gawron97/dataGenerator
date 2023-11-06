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
    private final List<String> descriptions = List.of("Chciałbym zgłosić problem z hałasem pochodzącym z pokoju studenta o numerze [numer pokoju]. Od pewnego czasu hałas jest nie do zniesienia, szczególnie wieczorami, kiedy próbuję się skoncentrować na nauce. Proszę o podjęcie działań w tej sprawie, aby wszyscy mieszkańcy mogli korzystać z ciszy i spokoju.", "Zwracam uwagę na niedostateczną dbałość o czystość i porządek w kuchni wspólnej na naszym piętrze. Często jest brudno, a naczynia zostawiane są na umywalce przez długi czas. Proszę o zaostrzenie przepisów dotyczących utrzymania porządku i egzekwowanie tych zasad wśród mieszkańców.", "Chciałbym zgłosić kilka przypadków naruszeń regulaminu akademika przez jednego z mieszkańców. Widziałem, jak parkuje swoje rower w miejscu niedozwolonym, a także zostawia rower w korytarzu, co utrudnia swobodne poruszanie się innym mieszkańcom. Proszę o podjęcie działań w tej sprawie i przypomnienie wszystkim o obowiązujących zasadach.", "Ostatnio miałem poważne problemy z dostępem do prysznica na naszym piętrze. Często brakuje ciepłej wody, a także woda nie spływa odpowiednio z odpływu. Proszę o pilne naprawienie tego problemu, ponieważ utrudnia to codzienne życie mieszkańców.", "Chciałbym zgłosić problem związany z bezpieczeństwem na naszym piętrze. Kilka razy widziałem osobę wchodzącą do budynku akademika bez karty dostępu, co stanowi zagrożenie dla bezpieczeństwa mieszkańców. Proszę o wdrożenie dodatkowych środków bezpieczeństwa, aby uniknąć takich sytuacji.");
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
                    .description(getFieldFillingProbability(75) ?
                                    descriptions.get(faker.number().numberBetween(0, descriptions.size())) : null)
                    .student(chosenStudent)
                    .build();

            complainRepository.save(complain);
        }
    }

    private Student getRandomStudent() {
        return students.get(faker.number().numberBetween(0, students.size()));
    }

}
