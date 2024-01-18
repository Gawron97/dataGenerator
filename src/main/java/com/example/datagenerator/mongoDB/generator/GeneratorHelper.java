package com.example.datagenerator.mongoDB.generator;

import com.example.datagenerator.jpa.entity.Manager;
import com.example.datagenerator.jpa.entity.Statute;
import com.example.datagenerator.jpa.entity.User;
import com.example.datagenerator.jpa.repository.ManagerRepository;
import com.example.datagenerator.jpa.repository.StatuteRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class GeneratorHelper {

    protected final Faker faker;

    protected final List<String> userAdditionalInfo = List.of(
            "Uczulenie na orzechy",
            "Uczulenie na gluten",
            "Uczulenie na laktozę",
            "Wegetarianin",
            "Weganin",
            "Nietolerancja laktozy",
            "Dieta bezglutenowa",
            "Choroba trzewna",
            "Celiakia",
            "Dieta niskokaloryczna",
            "Dieta wysokobiałkowa",
            "Uczulenie na plesń",
            "Uczulenie na pyłki",
            "Choroba serca",
            "Choroba nerek",
            "Cukrzyca",
            "Weganin z opcją na jajka",
            "Wegańska dieta bezglutenowa",
            "Vegetarianin z opcją na ryby",
            "Czarnoskóry",
            "Dieta ketogeniczna"
    );
    protected final List<String> titles = List.of("Zaniedbanie", "Zgloszenie", "Naduzycie", "Halas");
    protected final List<String> addidtionalInformations = List.of("Szanowna Administracja Akademika,\n" +
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


    protected boolean getFieldFillingProbability(int percentage) {
        return faker.number().numberBetween(0, 101) <= percentage;
    }

    protected User getRandomUserFromList(List<User> users) {
        if (users.isEmpty()) {
            return null;
        }

        int randomIndex = faker.number().numberBetween(0, users.size());
        return users.remove(randomIndex);
    }

    protected LocalDate getRandomDateLaterThen(LocalDate minDate) {
        return getRandomDateBetween(minDate, LocalDate.now());
    }

    protected LocalDate getRandomDateBetween(LocalDate minDate, LocalDate maxDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay + 1, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    protected Long getSeniority(LocalDate maxDate) {
        return (long) faker.number().numberBetween(0, 2023 - maxDate.getYear() + 1);
    }


    protected String getQualifications() {
        List<String> qualifications = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 5); i++) {
            qualifications.add(faker.job().keySkills());
        }
        return String.join(", ", qualifications);
    }

    protected String getWorkSchedule() {

        StringBuilder schedule = new StringBuilder();
        List<String> days = List.of("Pon", "Wt", "Sr", "Czw", "Pt", "Sob", "Nied");
        Set<Integer> randomDays = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            randomDays.add(faker.number().numberBetween(0, 7));
        }
        randomDays.forEach(integer -> schedule.append(MessageFormat.format("{0}\n", getWorkingDay(days.get(integer)))));
        return schedule.toString();
    }

    private String getWorkingDay(String day) {
        int startWorkHour = faker.number().numberBetween(6, 11);
        int endWorkHour = faker.number().numberBetween(18, 24);
        return MessageFormat.format("{0}  {1}-{2}", day, startWorkHour, endWorkHour);
    }

    protected String getZipCode() {
        String zipCode = String.valueOf(faker.number().numberBetween(10, 100));
        zipCode += "-";
        zipCode += String.valueOf(faker.number().numberBetween(100, 1000));
        return zipCode;
    }

    protected String getHouseNumber() {
        int probability = faker.number().numberBetween(0, 10);
        String houseNumber = String.valueOf(faker.number().numberBetween(1, 150));
        if (probability == 3) {
            houseNumber += "A";
        } else if (probability == 7) {
            houseNumber += "B";
        }
        return houseNumber;
    }

    protected String getPhoneNumber() {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            number.append(faker.number().numberBetween(0, 10));
        }
        return number.toString();
    }

    protected Manager getRandomManager(ManagerRepository managerRepository) {
        List<Manager> managers = managerRepository.findAll();
        if (managers.isEmpty()) {
            return null;
        }
        int randomIndex = faker.number().numberBetween(0, managers.size());
        return managers.get(randomIndex);
    }

    protected Statute getRandomStatute(StatuteRepository statuteRepository) {
        List<Statute> statutes = statuteRepository.findAll();
        if (statutes.isEmpty()) {
            return null;
        }
        int randomIndex = faker.number().numberBetween(0, statutes.size());
        return statutes.get(randomIndex);
    }

}
