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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DormitorySecurityGenerator extends GeneratorHelper {

    private final List<Dormitory> dormitories;
    private final List<Security> securities;
    private final DormitorySecurityRepository dormitorySecurityRepository;

    public DormitorySecurityGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository, SecurityRepository securityRepository, DormitorySecurityRepository dormitorySecurityRepository) {
        super(faker, userRepository);
        this.dormitorySecurityRepository = dormitorySecurityRepository;
        dormitories = dormitoryRepository.findAll();
        securities = securityRepository.findAll();
    }

    public void generateDormitorySecurity() {

        dormitories.forEach(dormitory -> {

            Set<Security> securities = getRandomSecurities(5);
            securities.forEach(security -> {
                DormitorySecurityId dormitorySecurityId = new DormitorySecurityId(dormitory, security);
                DormitorySecurity dormitorySecurity = DormitorySecurity.builder()
                        .id(dormitorySecurityId)
                        .salary(faker.number().numberBetween(3000, 8000))
                        .seniority((long) faker.number().numberBetween(0, 2023 - security.getUser().getRegistrationDate().getYear() + 1))
                        .build();

                dormitorySecurityRepository.save(dormitorySecurity);
            });

        });

    }

    private Set<Security> getRandomSecurities(int number) {
        Set<Security> securities = new HashSet<>();
        for (int i = 0; i <number; i++) {
            securities.add(this.securities.get(faker.number().numberBetween(0, this.securities.size())));
        }
        return securities;
    }

}
