package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.*;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;

import java.util.List;

public class ApplicationGenerator extends GeneratorHelper {

    private final ApplicationRepository applicationRepository;
    private final List<Student> students;
    private final List<OfficeWorker> officeWorkers;
    private final List<ApplicationType> applicationTypes;
    private final List<ApplicationStatus> applicationStatuses;
    private final List<String> addidtionalInformations = List.of("Szanowna Administracja Akademika,\n" +
            "\n" +
            "Zwracam się z prośbą o rozpatrzenie możliwości zmiany mojego obecnego pokoju. Z różnych powodów uważam, że przeniesienie do innego pomieszczenia byłoby dla mnie bardziej korzystne. Proszę o informacje dotyczące procedury i ewentualnych dostępnych opcji.", "Drodzy Pracownicy Administracji,\n" +
            "\n" +
            "Chciałbym uzyskać szczegółowe informacje na temat opłat związanych z zakwaterowaniem na nowy semestr. Czy istnieją jakieś nowe regulacje dotyczące opłat za mieszkanie? Proszę również o informacje dotyczące terminów płatności.", "Szanowni Państwo,\n" +
            "\n" +
            "Zwracam się z prośbą o przedłużenie mojego zakwaterowania o dodatkowy okres. Ze względu na okoliczności życiowe potrzebuję trochę więcej czasu na znalezienie nowego miejsca zamieszkania. Czy istnieje możliwość przedłużenia mojego obecnego kontraktu?", "Chciałbym zgłosić pewne kwestie dotyczące warunków mieszkania w moim obecnym pokoju. Niestety, napotykam na problemy z instalacją elektryczną i chciałbym poprosić o przegląd oraz ewentualne naprawy. Proszę o szybką reakcję w tej sprawie.", "Drodzy Pracownicy Administracji,\n" +
            "\n" +
            "Chciałbym zapytać, czy istnieje możliwość uzyskania dodatkowych udogodnień w moim pokoju, takich jak biurko czy regały. Obecnie zauważyłem brak pewnych elementów, które ułatwiłyby mi pracę nad studiami. Będę wdzięczny za rozważenie mojej prośby.", "Szanowne Biuro Administracji,\n" +
            "\n" +
            "Mam zamiar opuścić akademik po zakończeniu semestru i chciałbym uzyskać informacje na temat polityki zwrotu kaucji. Jakie są warunki zwrotu kaucji, i czy istnieją jakieś konkretne kroki, które powinienem podjąć w celu zabezpieczenia zwrotu kaucji?");


    public ApplicationGenerator(Faker faker, UserRepository userRepository, ApplicationRepository applicationRepository,
                                StudentRepository studentRepository, OfficeWorkerRepository officeWorkerRepository,
                                ApplicationTypeRepository applicationTypeRepository,
                                ApplicationStatusRepository applicationStatusRepository) {
        super(faker, userRepository);
        this.applicationRepository = applicationRepository;
        students = studentRepository.findAll();
        officeWorkers = officeWorkerRepository.findAll();
        applicationTypes = applicationTypeRepository.findAll();
        applicationStatuses = applicationStatusRepository.findAll();
    }

    public void generateApplications(int numberOfApplications) {
        for (int i = 0; i < numberOfApplications; i++) {
            Student chosenStudent = getRandomStudent();

            Application application = Application.builder()
                    .submissionDate(getRandomDateLaterThen(chosenStudent.getUser().getRegistrationDate()))
                    .preferredLocation(faker.address().streetName())
                    .additionalInformation(addidtionalInformations.get(faker.number().numberBetween(0, addidtionalInformations.size())))
                    .student(chosenStudent)
                    .officeWorker(getRandomOfficeWorker())
                    .applicationType(getRandomApplicationType())
                    .applicationStatus(getRandomApplicationStatus())
                    .build();
            applicationRepository.save(application);
        }
    }

    private Student getRandomStudent() {
        return students.get(faker.number().numberBetween(0, students.size()));
    }

    private OfficeWorker getRandomOfficeWorker() {
        return officeWorkers.get(faker.number().numberBetween(0, officeWorkers.size()));
    }

    private ApplicationType getRandomApplicationType() {
        return applicationTypes.get(faker.number().numberBetween(0, applicationTypes.size()));
    }

    private ApplicationStatus getRandomApplicationStatus() {
        return applicationStatuses.get(faker.number().numberBetween(0, applicationStatuses.size()));
    }

}
