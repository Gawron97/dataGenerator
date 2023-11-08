package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Contract;
import com.example.datagenerator.entity.Payment;
import com.example.datagenerator.entity.PaymentStatus;
import com.example.datagenerator.entity.Student;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.ContractRepository;
import com.example.datagenerator.repository.PaymentRepository;
import com.example.datagenerator.repository.PaymentStatusRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;




public class ContractPaymentGenerator extends GeneratorHelper {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final List<PaymentStatus> statuses;


    public ContractPaymentGenerator(Faker faker,UserRepository userRepository, ContractRepository contractRepository, PaymentRepository paymentRepository,
                                    PaymentStatusRepository paymentStatusRepository){

        super(faker, userRepository);
        this.contractRepository = contractRepository;
        this.paymentRepository = paymentRepository;
        this.statuses = paymentStatusRepository.findAll();

    }
@Transactional
    public void generateContractsPayments(){

        List<Contract> contracts = contractRepository.findAll();

        contracts.forEach(contract -> {
            Payment currentPayment = createPayment(contract);
            currentPayment.getContracts().add(contract);
            paymentRepository.save(currentPayment);
        } );


    }

    private void generatePaymentsForContract(Contract contract){

        int number_of_payments = faker.number().numberBetween(0,6);

        for( int i = 0; i<number_of_payments; i++){

            Payment currentPayment = createPayment(contract);
            currentPayment.getContracts().add(contract);
            paymentRepository.save(currentPayment);

        }

    }

    public Payment createPayment(Contract contract){

        return Payment.builder()
                .description("Opłata za wynajem")
                .creationDate(getRandomDateBetween(contract.getStart_date(), contract.getEnd_date()))
                .price(new BigDecimal(faker.number().randomDouble(2,1,1000)))
                .student(contract.getStudent())
                .paymentStatus(statuses.get(faker.number().numberBetween(0,statuses.size())))
                .contracts(new HashSet<>())
                .build();
    }
}