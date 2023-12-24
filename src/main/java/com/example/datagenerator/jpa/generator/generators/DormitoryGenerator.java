package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Dormitory;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.DormitoryRepository;
import com.example.datagenerator.jpa.repository.ManagerRepository;
import com.example.datagenerator.jpa.repository.StatuteRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;

import java.util.List;

public class DormitoryGenerator extends GeneratorHelper {

    private final DormitoryRepository dormitoryRepository;
    private final ManagerRepository managerRepository;
    private final StatuteRepository statuteRepository;

    private final List<String> dormitories =
            List.of("T2", "T3", "T4", "T6", "T15", "T16", "T17", "T18", "T19");

    private final List<String> descriptions = List.of(
            "Ten akademik to nowoczesny budynek z pełnym wyposażeniem kuchni i łazienek.",
            "Ten akademik oferuje pokoje jednoosobowe i dwuosobowe z dostępem do internetu.",
            "Ten akademik znajduje się blisko kampusu i ma wspaniały widok na miasto.",
            "Ten akademik to doskonałe miejsce do nauki i spotkań towarzyskich.",
            "Ten akademik oferuje duże sale klubowe i siłownię dla studentów.",
            "Ten akademik jest przyjazny dla zwierząt i posiada teren rekreacyjny.",
            "Ten akademik to idealne miejsce dla miłośników sportu z boiskiem i salą fitness.",
            "Ten akademik zapewnia pełne wyżywienie i ma własną bibliotekę.",
            "Ten akademik jest dogodnie położony w centrum miasta z dostępem do komunikacji publicznej."
    );

    public DormitoryGenerator(Faker faker, UserRepository userRepository, DormitoryRepository dormitoryRepository,
                              ManagerRepository managerRepository, StatuteRepository statuteRepository) {
        super(faker, userRepository);
        this.dormitoryRepository = dormitoryRepository;
        this.managerRepository = managerRepository;
        this.statuteRepository = statuteRepository;
    }


    public void generateDormitories() {

        for (int i = 0; i < dormitories.size(); i++) {
            Dormitory dorm = Dormitory.builder()
                    .name(dormitories.get(i))
                    .city(faker.address().city())
                    .street(faker.address().streetName()).numberOfStreet(faker.address().streetAddressNumber())
                    .description(descriptions.get(i))
                    .contactInfo(super.getPhoneNumber()).manager(super.getRandomManager(this.managerRepository))
                    .statute(super.getRandomStatute(this.statuteRepository)).build();


            dormitoryRepository.save(dorm);
        }

    }
}
