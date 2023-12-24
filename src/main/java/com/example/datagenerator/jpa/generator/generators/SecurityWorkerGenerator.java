package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Security;
import com.example.datagenerator.jpa.entity.User;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.SecurityRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

public class SecurityWorkerGenerator extends GeneratorHelper {
    private final SecurityRepository securityRepository;

    public SecurityWorkerGenerator(Faker faker, SecurityRepository securityRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.securityRepository = securityRepository;
    }

    public void generateSecurity(int numberOfSecurities) {

        for (int i = 0; i < numberOfSecurities; i++) {
            User chosenUser = generateRandomUser();
            Security security = generateSecurityForUser(chosenUser);
            securityRepository.save(security);
        }
    }

    private Security generateSecurityForUser(User user) {
        return Security.builder()
                .qualifications(getQualifications())
                .workSchedule(getWorkSchedule())
                .user(user)
                .build();
    }
}
