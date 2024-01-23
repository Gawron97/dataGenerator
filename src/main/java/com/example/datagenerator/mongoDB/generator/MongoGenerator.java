package com.example.datagenerator.mongoDB.generator;

import com.example.datagenerator.mongoDB.model.*;
import com.example.datagenerator.mongoDB.repository.DormitoryMongoRepository;
import com.example.datagenerator.mongoDB.repository.OfficeWorkerMongoRepository;
import com.example.datagenerator.mongoDB.repository.RoomMongoRepository;
import com.example.datagenerator.mongoDB.repository.StudentMongoRepository;
import com.github.javafaker.Faker;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MongoGenerator extends GeneratorHelper {

    private final StudentMongoRepository studentRepository;
    private final RoomMongoRepository roomRepository;
    private final OfficeWorkerMongoRepository officeWorkerRepository;

    private final DormitoryMongoRepository dormitoryMongoRepository;

    public MongoGenerator(Faker faker, StudentMongoRepository studentRepository, RoomMongoRepository roomRepository, OfficeWorkerMongoRepository officeWorkerRepository, DormitoryMongoRepository dormitoryMongoRepository) {
        super(faker);
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.officeWorkerRepository = officeWorkerRepository;
        this.dormitoryMongoRepository = dormitoryMongoRepository;
    }

    public void generate() {

        //generateDormitories -> i tutaj bedzie to generowane razem z rooms, ktore pozniej pobierzemy do studentow

        generateDormitories();
        List<Room> rooms = roomRepository.findAll();
        List<OfficeWorker> officeWorkers = officeWorkerRepository.findAll();

        generateStudents(10000, rooms, officeWorkers);


    }

    private void generateStudents(int quantity, List<Room> rooms, List<OfficeWorker> officeWorkers) {

        for(int i=0; i<quantity; i++) {

            LocalDate registrationDate = getRandomDateBetween(LocalDate.of(2010, 1, 11),
                    LocalDate.of(2023, 9, 26));
            LocalDate lastLoginDate = getRandomDateLaterThen(registrationDate);
            int maxAcademicYear = Integer.min(4, 2023 - registrationDate.getYear() + 2);

            var mail = faker.internet().emailAddress();
            while (studentRepository.existsByEmail(mail)) {
                mail = faker.internet().emailAddress();
            }

            Student student = Student.builder()
                    .academicYear((long) faker.number().numberBetween(1, maxAcademicYear))
                    .domicile(getFieldFillingProbability(75) ? faker.address().cityName() : null)
                    .street(getFieldFillingProbability(60) ? faker.address().streetName() : null)
                    .houseNumber(getFieldFillingProbability(75) ? getHouseNumber() : null)
                    .apartmentNumber(getFieldFillingProbability(25) ? String.valueOf(faker.number().numberBetween(0, 40)) : null)
                    .zipCode(getFieldFillingProbability(75) ? getZipCode() : null)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(mail)
                    .password(faker.internet().password())
                    .registrationDate(registrationDate)
                    .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                    .isEnabled(faker.bool().bool())
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .additionalInfo(getFieldFillingProbability(10) ? userAdditionalInfo.get(faker.number().numberBetween(0, userAdditionalInfo.size())) : null )
                    .payments(generatePayments(faker.number().numberBetween(0, 200), registrationDate))
                    .contracts(generateContracts(faker.number().numberBetween(0, 500), registrationDate, rooms))
                    .complains(generateComplains(faker.number().numberBetween(0, 100), registrationDate))
                    .studies(generateStudies(faker.number().numberBetween(0, 3)))
                    .applications(generateApplications(faker.number().numberBetween(0, 40), registrationDate, officeWorkers))
                    .build();

            studentRepository.save(student);
        }

    }

    private Set<Application> generateApplications(int quantity, LocalDate registrationDate, List<OfficeWorker> officeWorkers) {
        Set<Application> applications = new HashSet<>(quantity);
        for(int i=0; i<quantity; i++) {

            LocalDate startDate = getRandomDateLaterThen(registrationDate);
            LocalDate endDate = getRandomDateBetween(startDate, LocalDate.now());

            Application application = Application.builder()
                    .submissionDate(startDate)
                    .preferredLocation(getFieldFillingProbability(75) ? faker.address().cityName() : null)
                    .additionalInformation(getFieldFillingProbability(10) ?
                            addidtionalInformations.get(faker.number().numberBetween(0, addidtionalInformations.size())) : null)
                    .startDate(startDate)
                    .endDate(endDate)
                    .applicationStatus(ApplicationStatus.values()[faker.number().numberBetween(0, ApplicationStatus.values().length)])
                    .applicationType(ApplicationType.values()[faker.number().numberBetween(0, ApplicationType.values().length)])
                    .officeWorker(officeWorkers.get(faker.number().numberBetween(0, officeWorkers.size())))
                    .build();
            applications.add(application);
        }
        return applications;
    }

    private Set<Studies> generateStudies(int quantity) {
        Set<Studies> studies = new HashSet<>(quantity);
        for(int i=0; i<quantity; i++) {
            Studies study = Studies.builder()
                    .fieldName(faker.educator().course())
                    .fieldLevel(faker.educator().secondarySchool())
                    .facultyName(faker.university().name())
                    .facultyDeanOfficeLocation(faker.address().cityName())
                    .facultyContactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .facultyEmailAddress(getFieldFillingProbability(75) ? faker.internet().emailAddress() : null)
                    .build();
            studies.add(study);
        }
        return studies;
    }

    private Set<Complain> generateComplains(int quantity, LocalDate registrationDate) {
        Set<Complain> complains = new HashSet<>(quantity);
        for(int i=0; i<quantity; i++) {
            Complain complain = Complain.builder()
                    .submissionDate(getRandomDateLaterThen(registrationDate))
                    .author(getFieldFillingProbability(75) ? faker.funnyName().name() : null)
                    .title(titles.get(faker.number().numberBetween(0, titles.size())))
                    .description("")
                    .build();
            complains.add(complain);
        }
        return complains;
    }

    private void generateDormitories(){

        for(int i = 0; i < dormitories.size(); i++){

            Dormitory dormitory = Dormitory.builder()
                    .name(dormitories.get(i))
                    .city(faker.address().city())
                    .street(faker.address().streetName())
                    .numberOfStreet(faker.address().streetAddressNumber())
                    .description(dormitoryDescriptions.get(faker.number().numberBetween(0, dormitoryDescriptions.size())))
                    .contactInfo(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .statute(generateStatute())
                    .manager(generateManager())
                    .requirements(generateRequirements(3))
                    .additionalServices(generateAdditionalServices(3))
                    .floors(generateFloors(3,10))
                    .securities(generateSecurity(3))
                    .build();

            dormitoryMongoRepository.save(dormitory);
        }


    }



    private Set<Contract> generateContracts(int quantity, LocalDate registrationDate, List<Room> rooms) {
        Set<Contract> contracts = new HashSet<>(quantity);
        for(int i=0; i<quantity; i++) {

            LocalDate startDate = getRandomDateLaterThen(registrationDate);

            Contract contract = Contract.builder()
                    .start_date(startDate)
                    .end_date(getRandomDateLaterThen(startDate))
                    .room(rooms.get(faker.number().numberBetween(0, rooms.size())))
                    .payments(generatePayments(faker.number().numberBetween(0, 500), registrationDate))
                    .build();

            contracts.add(contract);
        }
        return contracts;
    }

    private Set<Payment> generatePayments(int quantity, LocalDate registrationDate) {
        Set<Payment> payments = new HashSet<>(quantity);
        for(int i=0; i<quantity; i++) {
            Payment payment = Payment.builder()
                    .description("")
                    .creationDate(getRandomDateLaterThen(registrationDate))
                    .price(BigDecimal.valueOf(faker.number().numberBetween(0, 1000)))
                    .paymentStatus(PaymentStatus.values()[faker.number().numberBetween(0, PaymentStatus.values().length)])
                    .build();
            payments.add(payment);
        }
        return payments;
    }


    private Set<Requirements> generateRequirements(int quantity){

        Set<Requirements> requirements = new HashSet<>(quantity);
        for(int i = 0; i < quantity; i++){

            Requirements req = Requirements.builder().criterion(requirement_criteria.get(faker.number().numberBetween(0, requirement_criteria.size())))
                    .build();
            requirements.add(req);

        }
        return requirements;
    }

    private Statute generateStatute(){


        return Statute.builder()
                .content(contents.get(faker.number().numberBetween(0, contents.size())))
                .additional_info(statute_info.get(faker.number().numberBetween(0, statute_info.size())))
                .build();


    }

    private Set<AdditionalServices> generateAdditionalServices(int quantity){

        Object[] keysArray = additionalService.keySet().toArray();

        Set<AdditionalServices> services = new HashSet<>(quantity);
        for(int i = 0; i < quantity; i++){

            int randomIndex = new Random().nextInt(keysArray.length);
            String key = (String)keysArray[randomIndex];

            AdditionalServices service = AdditionalServices.builder()
                    .name(key)
                    .description(additionalService.get(key))
                    .build();

            services.add(service);
        }
        return services;
    }

    private Set<Security> generateSecurity(int quantity){

        Set<Security> security = new HashSet<>(quantity);

        for(int i = 0; i < quantity; i++){

            LocalDate registrationDate = getRandomDateBetween(LocalDate.of(2010, 1, 11),
                    LocalDate.of(2023, 9, 26));
            LocalDate lastLoginDate = getRandomDateLaterThen(registrationDate);

            var mail = faker.internet().emailAddress();
            while (studentRepository.existsByEmail(mail)) {
                mail = faker.internet().emailAddress();
            }


            Security sec = Security.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(mail)
                    .password(faker.internet().password())
                    .registrationDate(registrationDate)
                    .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                    .isEnabled(faker.bool().bool())
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .qualifications(getQualifications())
                    .workSchedule(getWorkSchedule())
                    .additionalInfo("No info")
                    .build();
            security.add(sec);

        }

        return security;
    }

    private Manager generateManager(){





            LocalDate registrationDate = getRandomDateBetween(LocalDate.of(2010, 1, 11),
                    LocalDate.of(2023, 9, 26));
            LocalDate lastLoginDate = getRandomDateLaterThen(registrationDate);

            var mail = faker.internet().emailAddress();
            while (studentRepository.existsByEmail(mail)) {
                mail = faker.internet().emailAddress();
            }
           return  Manager.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(mail)
                    .password(faker.internet().password())
                    .registrationDate(registrationDate)
                    .lastLogin(getFieldFillingProbability(75) ? lastLoginDate : null)
                    .isEnabled(faker.bool().bool())
                    .contactNumber(getFieldFillingProbability(75) ? getPhoneNumber() : null)
                    .workSchedule(getWorkSchedule())
                    .additionalInfo("No info")
                    .seniority((long) faker.number().numberBetween(1,20))
                    .build();




    }


    private Set<AdditionalRooms> generateAdditionalRooms(int quantity){


        Set<AdditionalRooms> rooms = new HashSet<>(quantity);
        for(int i = 0; i < quantity; i++){

            AdditionalRooms room = AdditionalRooms.builder()
                    .roomNumber(faker.number().numberBetween(1,30))
                    .description(addRoomDescriptions.get(faker.number().numberBetween(0, addRoomDescriptions.size())))
                    .additionalRoomType(getRandomAdditionalRoomType())
                    .build();

            rooms.add(room);
        }
        return rooms;
    }


    private Set<Room> generateRooms(int minQuantity, int maxQuantity){

        int quantity = faker.number().numberBetween(minQuantity, maxQuantity);

        Set<Room> rooms = new HashSet<>(quantity);

        for(int i = 0; i < quantity; i++){

            BigDecimal size = BigDecimal.valueOf(faker.number().randomDouble(2,10,21));
            Long numberOfBeds = (long) getRandomNumberOfBeds();

            Room room = Room.builder()
                    .roomNumber(String.valueOf(i+1))
                    .description("brak")
                    .numberOfBeds(numberOfBeds)
                    .freeBeds(numberOfBeds)
                    .has_a_bathroom(true)
                    .price(size.multiply(BigDecimal.valueOf(40.0)))
                    .size(size)
                    .isAvailable(true)
                    .roomType(getRandomRoomType())
                    .build();
            rooms.add(room);
        }
    return rooms;
    }


    private Set<Floor> generateFloors(int minQuantity, int maxQuantity){

        int quantity = faker.number().numberBetween(minQuantity, maxQuantity);

        Set<Floor> floors = new HashSet<>(quantity);

        for(int i = 0; i < quantity; i++){

            Floor floor = Floor.builder()
                    .level((long) i+1)
                    .description(floorDescriptions.get(faker.number().numberBetween(0, floorDescriptions.size())))
                    .isAvailable(true)
                    .rooms(generateRooms(10,20))
                    .additionalRooms(generateAdditionalRooms(3))
                    .build();

            floors.add(floor);
        }
        return floors;
    }













}
