package com.example.datagenerator;

import com.example.datagenerator.generator.DataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataGeneratorApplication implements CommandLineRunner {
    private static DataGenerator dataGenerator;
    public DataGeneratorApplication(DataGenerator generator) {
        dataGenerator = generator;
    }

    //numberBetween(0, 2) wygeneruje 0 lub 1

    public static void main(String[] args) {
        SpringApplication.run(DataGeneratorApplication.class, args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
//        dataGenerator.generateUsers(20);
//        dataGenerator.generateManagers(4);
//        dataGenerator.generateStudents(10);
        dataGenerator.generateSecurity(10);
//        dataGenerator.generateOfficeWorker(4);
    }
}
