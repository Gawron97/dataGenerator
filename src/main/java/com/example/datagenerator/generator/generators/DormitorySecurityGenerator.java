package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Dormitory;
import com.example.datagenerator.entity.DormitorySecurity;
import com.example.datagenerator.entity.DormitorySecurityId;
import com.example.datagenerator.entity.Security;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.DormitoryRepository;
import com.example.datagenerator.repository.DormitorySecurityRepository;
import com.example.datagenerator.repository.SecurityRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class DormitorySecurityGenerator extends GeneratorHelper {

    private final DormitoryRepository dormitoryRepository;
    private final SecurityRepository securityRepository;
    private final DormitorySecurityRepository dormitorySecurityRepository;

    public DormitorySecurityGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository, SecurityRepository securityRepository, DormitorySecurityRepository dormitorySecurityRepository) {
        super(faker, userRepository);
        this.dormitoryRepository = dormitoryRepository;
        this.securityRepository = securityRepository;
        this.dormitorySecurityRepository = dormitorySecurityRepository;
    }

    public void generateDormitorySecurity() {

        List<Dormitory> dormitories = dormitoryRepository.findAll();
        List<Security> securities = securityRepository.findAll();

        dormitories.forEach(dormitory -> {

            Security security = securities.get(faker.number().numberBetween(0, securities.size()));
            DormitorySecurityId dormitorySecurityId = new DormitorySecurityId(dormitory, security);
            DormitorySecurity dormitorySecurity = DormitorySecurity.builder()
                    .id(dormitorySecurityId)
                    .salary(faker.number().numberBetween(3000, 8000))
                    .seniority((long) faker.number().numberBetween(0, 2023 - security.getUser().getRegistrationDate().getYear() + 1))
                    .build();

            dormitorySecurityRepository.save(dormitorySecurity);

        });

    }

}
