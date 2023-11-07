package com.example.datagenerator;

import com.example.datagenerator.generator.FillTable;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class DataGeneratorApplication implements CommandLineRunner {
    private static FillTable fillTable;

    public DataGeneratorApplication(FillTable fill) {
        fillTable = fill;
    }

    //numberBetween(0, 2) wygeneruje 0 lub 1

    public static void main(String[] args) {
        SpringApplication.run(DataGeneratorApplication.class, args);
//        fillTable.fillTable().user().generateUsers(10);
//        fillTable.fillTable().manager().generateManagers(2);
//        fillTable.fillTable().officeWorker().generateOfficeWorker(2);
//        fillTable.fillTable().securityWorker().generateSecurity(2);
//        fillTable.fillTable().student().generateStudents(2);
//        fillTable.fillTable().additionalRoomType().generateAdditionalRoomTypes();
//        fillTable.fillTable().additionalRoom().generateAdditionalRooms(20);
//        fillTable.fillTable().complain().generateComplains(15);
//        fillTable.fillTable().moduleType().generateModuleTypes();
//        fillTable.fillTable().module().generateModules(20);
//        fillTable.fillTable().facultyGenerator().generateFaculties();
//        fillTable.fillTable().statute().generateStatutes();
//        fillTable.fillTable().roomType().generateRoomTypes();
//        fillTable.fillTable().dormitory().generateDormitories();
//        fillTable.fillTable().floor().generateFloors();
//        fillTable.fillTable().room().generateRooms(5,10);
        fillTable.fillTable().contract().generateContracts(LocalDate.of(2023,1,1), LocalDate.of(2023,12,31),10);

    }

    @Override
    @SneakyThrows
    public void run(String... args) {
//        fillTable.fillTable().user().generateUsers(10);
//        fillTable.fillTable().manager().generateManagers(2);
//        fillTable.fillTable().officeWorker().generateOfficeWorker(2);
//        fillTable.fillTable().securityWorker().generateSecurity(2);
//        fillTable.fillTable().student().generateStudents(2);
    }
}
