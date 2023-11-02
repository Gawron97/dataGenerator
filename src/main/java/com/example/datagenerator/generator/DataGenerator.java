package com.example.datagenerator.generator;

import com.example.datagenerator.entity.*;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataGenerator {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final SecurityRepository securityRepository;
    private final OfficeWorkerRepository officeWorkerRepository;
    private final Faker faker = new Faker();

    public void generateUsers(int numberOfUsers) {

        for (int i = 0; i < numberOfUsers; i++) {

            Date fakerRegistrationDate = faker.date().between(new Date(2010-1900, 01, 01),
                    new Date(2023-1900, 10, 26));
            Date fakerLastLoginDate = faker.date().between(fakerRegistrationDate,
                    new Date(2023-1900, 10, 30));
            LocalDate registrationDate = fakerRegistrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate lastLoginDate = fakerLastLoginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            User user = User.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .registrationDate(registrationDate)
                    .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                    .isEnabled(faker.bool().bool())
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .build();

            userRepository.save(user);
        }

    }

    public void generateManagers(int numberOfManagers) {

        List<User> unsignedUsers = getUnsignedUsers();

        if(unsignedUsers.size() >= numberOfManagers) {
            for (int i = 0; i < numberOfManagers; i++) {

                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                Manager manager = Manager.builder()
                        .workSchedule(getFieldFillingProbability(75) ? getWorkSchedule() : null)
                        .seniority(getSeniority(chosenUser.getRegistrationDate()))
                        .user(chosenUser)
                        .build();

                managerRepository.save(manager);

            }
        }

    }

    public void generateStudents(int numberOfStudent) {

        List<User> unsignedUsers = getUnsignedUsers();
        if(unsignedUsers.size() >= numberOfStudent) {
            for (int i = 0; i < numberOfStudent; i++) {

                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                Student student = Student.builder()
                        .academicYear(faker.number().numberBetween(1,
                                Integer.min(4, 2023 - chosenUser.getRegistrationDate().getYear() + 2)))
                        .domicile(getFieldFillingProbability(75) ? faker.address().cityName() : null)
                        .street(getFieldFillingProbability(60) ? faker.address().streetName() : null)
                        .houseNumber(getFieldFillingProbability(75) ? getHouseNumber() : null)
                        .apartmentNumber(getFieldFillingProbability(25) ? String.valueOf(faker.number().numberBetween(0, 40)) : null)
                        .zipCode(getFieldFillingProbability(75) ? getZipCode() : null)
                        .user(chosenUser)
                        .build();

                studentRepository.save(student);
            }
        }
    }

    public void generateSecurity(int numberOfSecurities) {

        List<User> unsignedUsers = getUnsignedUsers();
        if(unsignedUsers.size() >= numberOfSecurities) {
            for(int i=0; i<numberOfSecurities; i++) {
                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                Security security = Security.builder()
                        .qualifications(getQualifications())
                        .workSchedule(getWorkSchedule())
                        .user(chosenUser)
                        .build();

                securityRepository.save(security);
            }
        }
    }

    public void generateOfficeWorker(int numberOfOfficeWorkers) {

        List<User> unsignedUsers = getUnsignedUsers();
        if(unsignedUsers.size() >= numberOfOfficeWorkers) {
            for(int i=0; i<numberOfOfficeWorkers; i++) {
                User chosenUser = unsignedUsers.remove(faker.number().numberBetween(0, unsignedUsers.size()));

                OfficeWorker officeWorker = OfficeWorker.builder()
                        .seniority(getSeniority(chosenUser.getRegistrationDate()))
                        .user(chosenUser)
                        .build();

                officeWorkerRepository.save(officeWorker);
            }
        }

    }

    private Long getSeniority(LocalDate maxDate) {
        return (long) faker.number().numberBetween(0, 2023 - maxDate.getYear() + 1);
    }

    private boolean getFieldFillingProbability(int percentage) {
        return faker.number().numberBetween(0, 101) <= percentage;
    }

    private String getQualifications() {
        List<String> qualifications = new ArrayList<>();
        for(int i=0; i<faker.number().numberBetween(1, 5); i++) {
            qualifications.add(faker.job().keySkills());
        }
        return String.join(", ", qualifications);
    }

    private String getWorkSchedule() {

        StringBuilder schedule = new StringBuilder();
        List<String> days = List.of("Pon", "Wt", "Sr", "Czw", "Pt", "Sob", "Nied");
        Set<Integer> randomDays = new HashSet<>();
        for(int i=0; i<7; i++) {
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

    private String getZipCode() {
        String zipCode = String.valueOf(faker.number().numberBetween(10, 100));
        zipCode += "-";
        zipCode += String.valueOf(faker.number().numberBetween(100, 1000));
        return zipCode;
    }

    private String getHouseNumber() {
        int probability = faker.number().numberBetween(0, 10);
        String houseNumber = String.valueOf(faker.number().numberBetween(1, 150));
        if(probability == 3) {
            houseNumber += "A";
        } else if(probability == 7) {
            houseNumber += "B";
        }
        return houseNumber;
    }

    private List<User> getUnsignedUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getManager() == null && user.getStudent() == null && user.getSecurity() == null)
                .collect(Collectors.toList());
        //TODO rozszerzyc o kolejnych urzytkownikow
    }

    private String getPhoneNumber() {
        StringBuilder number = new StringBuilder();
        for(int i=0; i<9; i++) {
            number.append(faker.number().numberBetween(0, 10));
        }
        return number.toString();
    }


}
