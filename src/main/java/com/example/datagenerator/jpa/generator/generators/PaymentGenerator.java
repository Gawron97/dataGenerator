package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Payment;
import com.example.datagenerator.jpa.entity.PaymentStatus;
import com.example.datagenerator.jpa.entity.Student;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.PaymentRepository;
import com.example.datagenerator.jpa.repository.PaymentStatusRepository;
import com.example.datagenerator.jpa.repository.StudentRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PaymentGenerator extends GeneratorHelper {

     private final PaymentRepository paymentRepository;

     private final StudentRepository studentRepository;

     private final List<PaymentStatus> statuses;

     private final List<String> descriptions;

     public PaymentGenerator(Faker faker, UserRepository userRepository, PaymentRepository paymentRepository,
                             StudentRepository studentRepository, PaymentStatusRepository paymentStatusRepository){
         super(faker, userRepository);
         this. paymentRepository = paymentRepository;
         this.studentRepository = studentRepository;
         this.statuses = paymentStatusRepository.findAll();
         this.descriptions = List.of("Opłata za wyrządzone szkody","Opłata za wyrobienie nowej karty mieszkańca",
                                    "Opłata za sprzątanie", "Opłata za wymianę kluczy");


     }

     public void generatePayments(int numberOfPayments){

         List<Student> students = studentRepository.findAll();

         for(int i = 0; i < numberOfPayments; i++){

             Student currentStudent = students.get(faker.number().numberBetween(0, students.size()));
             Payment currentPayment = createPayment(currentStudent);

             paymentRepository.save(currentPayment);
         }

     }

     public Payment createPayment(Student student){

         return Payment.builder()
                 .description(descriptions.get(faker.number().numberBetween(0,descriptions.size())))
                 .creationDate(getRandomDateBetween(student.getUser().getRegistrationDate(),
                         LocalDate.of(2023,12,31)))
                 .price((new BigDecimal(faker.number().randomDouble(2,10,1000))))
                 .student(student)
                 .paymentStatus(statuses.get(faker.number().numberBetween(0,statuses.size())))
                 .build();

     }


}
