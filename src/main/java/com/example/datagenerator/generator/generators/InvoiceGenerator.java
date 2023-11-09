package com.example.datagenerator.generator.generators;

import com.example.datagenerator.entity.Invoice;
import com.example.datagenerator.entity.Payment;
import com.example.datagenerator.entity.Student;
import com.example.datagenerator.generator.GeneratorHelper;
import com.example.datagenerator.repository.InvoiceRepository;
import com.example.datagenerator.repository.PaymentRepository;
import com.example.datagenerator.repository.UserRepository;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceGenerator extends GeneratorHelper {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    private static int newestInvoiceNumber;

    public InvoiceGenerator(Faker faker, UserRepository userRepository, InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        super(faker, userRepository);
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    public void generateInvoices(LocalDate periodStartDate, LocalDate periodEndDate, int numberOfInvoices){

        List<Payment> payments = paymentRepository.findAll();

        if(payments.size() >= numberOfInvoices) {

            for (int i = 0; i < numberOfInvoices; i++) {

                Payment currentPayment = payments.get(faker.number().numberBetween(0, payments.size()));
                LocalDate creationDate = getRandomDateBetween(periodStartDate,periodEndDate);
                LocalDate dueDate = getRandomDateLaterThen(creationDate);
                Invoice currentInvoice = createInvoice(currentPayment, creationDate, dueDate);

                invoiceRepository.save(currentInvoice);
            }
        }

    }

    private Invoice createInvoice(Payment payment, LocalDate creationDate, LocalDate dueDate){


        String title = "Faktura "+ newestInvoiceNumber;
        newestInvoiceNumber++;

        return Invoice.builder()
                .title(title)
                .creationDate(creationDate)
                .paymentDue(dueDate)
                .totalPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000)))
                .payment(payment)
                .build();

    }



}
