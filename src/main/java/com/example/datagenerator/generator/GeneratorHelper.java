package com.example.datagenerator.generator;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.Manager;
import com.example.datagenerator.entity.Statute;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.ManagerRepository;
import com.example.datagenerator.repository.StatuteRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class GeneratorHelper {
    protected final Faker faker;
    private final UserRepository userRepository;

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

    protected User generateRandomUser() {

        LocalDate registrationDate = getRandomDateBetween(LocalDate.of(2010, 1, 11),
                LocalDate.of(2023, 9, 26));
        LocalDate lastLoginDate = getRandomDateLaterThen(registrationDate);

        var mail = faker.internet().emailAddress();
        while (userRepository.existsByEmail(mail)) {
            mail = faker.internet().emailAddress();
        }

        return userRepository.save(User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(mail)
                .password(faker.internet().password())
                .registrationDate(registrationDate)
                .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                .isEnabled(faker.bool().bool())
                .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                .build());
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
