package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Security;
import com.example.datagenerator.entity.User;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.SecurityRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class SecurityWorkerGenerator extends GeneratorHelper {
    private final SecurityRepository securityRepository;
    public SecurityWorkerGenerator(Faker faker, SecurityRepository securityRepository, UserRepository userRepository) {
        super(faker, userRepository);
        this.securityRepository = securityRepository;
    }

    public void generateSecurity(int numberOfSecurities) {
//        List<User> unsignedUsers = getUnsignedUsers();
//
//        if (unsignedUsers.size() < numberOfSecurities) {
//            return;
//        }
//        for (int i = 0; i < numberOfSecurities; i++) {
//            User chosenUser = getRandomUserFromList(unsignedUsers);
//
//            if (chosenUser != null) {
//                Security security = generateSecurityForUser(chosenUser);
//                securityRepository.save(security);
//            }
//        }
    }

    private Security generateSecurityForUser(User user) {
        return Security.builder()
                .qualifications(getQualifications())
                .workSchedule(getWorkSchedule())
                .user(user)
                .build();
    }
}
