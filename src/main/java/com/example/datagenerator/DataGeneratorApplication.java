package com.example.datagenerator;

import com.example.datagenerator.jpa.generator.FillTable;
import com.example.datagenerator.mongoDB.generator.MongoGenerator;
import com.example.datagenerator.mongoDB.repository.*;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DataGeneratorApplication implements CommandLineRunner {
    private static FillTable fillTable;

    @Autowired
    private StudentMongoRepository studentMongoRepository;

    @Autowired
    private OfficeWorkerMongoRepository officeWorkerMongoRepository;

    @Autowired
    private DormitoryMongoRepository dormitoryMongoRepository;



//    public DataGeneratorApplication(FillTable fill) {
//        fillTable = fill;
//    }



    public static void main(String[] args) {
        SpringApplication.run(DataGeneratorApplication.class, args);

//        fillTable.fillTable().manager().generateManagers(10000);
//        fillTable.fillTable().officeWorker().generateOfficeWorker(5000);
//        fillTable.fillTable().securityWorker().generateSecurity(5000);
//        fillTable.fillTable().student().generateStudents(50000);
//        fillTable.fillTable().additionalRoomType().generateAdditionalRoomTypes();
//        fillTable.fillTable().additionalRoom().generateAdditionalRooms(500);
//        fillTable.fillTable().complain().generateComplains(150000);
//        fillTable.fillTable().moduleType().generateModuleTypes();
//        fillTable.fillTable().module().generateModules(200);
//        fillTable.fillTable().facultyGenerator().generateFaculties();
//        fillTable.fillTable().statute().generateStatutes();
//        fillTable.fillTable().roomType().generateRoomTypes();
//        fillTable.fillTable().dormitory().generateDormitories();
//        fillTable.fillTable().floor().generateFloors();
//        fillTable.fillTable().room().generateRooms(10,25);
//        fillTable.fillTable().contract().generateContracts( LocalDate.of(2022,1,1),
//                                                            LocalDate.of(2023,12,31),500);
//        fillTable.fillTable().paymentStatus().generatePaymentStatuses();
//        fillTable.fillTable().payment().generatePayments(20000);
//        fillTable.fillTable().applicationType().generateApplicationTypes();
//        fillTable.fillTable().applicationStatus().generateApplicationStatuses();
//        fillTable.fillTable().application().generateApplications(100000);
//        fillTable.fillTable().additionalServices().generateAdditionalServices();
//        fillTable.fillTable().requirements().generateRequirements();
//        fillTable.fillTable().fieldOfStudy().generateFieldsOfStudy();
//        fillTable.fillTable().contractPayment().generateContractsPayments();
//        fillTable.fillTable().dormitoryRequirementsGenerator().generateDormitoryRequirements();
//        fillTable.fillTable().dormitorySecurityGenerator().generateDormitorySecurity();
//        fillTable.fillTable().dormitoryServicesGenerator().generateDormitoryServices();
//        fillTable.fillTable().studentFieldOfStudiesGenerator().generateStudentFieldOfStudies();
//        fillTable.fillTable().invoice().generateInvoices(LocalDate.of(2022,1,1),
//                                                            LocalDate.of(2023,10,31),10000);
//        fillTable.fillTable().floorAdditionalRooms().generateFloorAdditionalRooms();



    }


    @Override
    @SneakyThrows
    public void run(String... args) {


        //        AdditionalServices additionalServices = AdditionalServices.builder()
//                .name("service1")
//                .build();
//
//        AdditionalServices additionalServices2 = AdditionalServices.builder()
//                .name("service2")
//                .build();
//
//        AdditionalServices additionalServices3 = AdditionalServices.builder()
//                .name("service3")
//                .build();
//
//        Dormitory dormitory = Dormitory.builder()
//                .name("dor1")
//                .build();
//
//        Dormitory dormitory2 = Dormitory.builder()
//                .name("dor2")
//                .build();
//
//        dormitory.setAdditionalServices(new HashSet<>(List.of(additionalServices, additionalServices2)));
//
//        dormitoryMongoRepository.save(dormitory);
//        additionalServicesMongoRepository.save(additionalServices);
//        additionalServicesMongoRepository.save(additionalServices2);

//        User user = User.builder()
//                .contactNumber("sd")
//                .isEnabled(true)
//                .lastName("sass")
//                .firstName("user2")
//                .build();
//
//        userMongoRepository.save(user);
//        fillTable.fillTable().user().generateUsers(10);
//        fillTable.fillTable().manager().generateManagers(2);
//        fillTable.fillTable().officeWorker().generateOfficeWorker(2);
//        fillTable.fillTable().securityWorker().generateSecurity(2);
//        fillTable.fillTable().student().generateStudents(2);


        new MongoGenerator(new Faker(), studentMongoRepository, officeWorkerMongoRepository, dormitoryMongoRepository).
                generate();


    }
}
