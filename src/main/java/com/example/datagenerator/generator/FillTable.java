package com.example.datagenerator.generator;

import com.example.datagenerator.generator.methods.*;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillTable {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final SecurityRepository securityRepository;
    private final OfficeWorkerRepository officeWorkerRepository;

    public TableGeneratorApi fillTable() {
        Faker faker = new Faker();
        return new TableGeneratorApi(new UserGenerator(faker, userRepository),
                new ManagerWorkerGenerator(faker, managerRepository, userRepository),
                new OfficeWorkerGenerator(faker, officeWorkerRepository, userRepository),
                new SecurityWorkerGenerator(faker, securityRepository, userRepository),
                new StudentGenerator(faker, studentRepository, userRepository));
    }
}
