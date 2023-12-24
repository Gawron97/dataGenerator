package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.PaymentStatus;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.PaymentStatusRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class PaymentStatusGenerator extends GeneratorHelper {

    private final PaymentStatusRepository paymentStatusRepository;
    private static final List<String> statuses = List.of("Rozpoczęta", "Anulowana","Zakończona","Nieudana");

    public PaymentStatusGenerator(Faker faker, UserRepository userRepository, PaymentStatusRepository paymentStatusRepository) {
        super(faker, userRepository);
        this.paymentStatusRepository = paymentStatusRepository;
    }

    public void generatePaymentStatuses() {
        statuses.forEach(type -> paymentStatusRepository.save(createRoomType(type)));
    }

    private PaymentStatus createRoomType(String type) {
        return PaymentStatus.builder()
                .status(type)
                .build();
    }
}