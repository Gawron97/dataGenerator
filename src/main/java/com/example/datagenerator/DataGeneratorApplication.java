package com.example.datagenerator;

import com.example.datagenerator.generator.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataGeneratorApplication implements CommandLineRunner {

    @Autowired
    private DataGenerator dataGenerator;

    public static void main(String[] args) {
        SpringApplication.run(DataGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        dataGenerator.generateUsers(10);
//        dataGenerator.generateManagers(4);
    }
}
