package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.AdditionalServices;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.AdditionalServicesRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class AdditionalServicesGenerator extends GeneratorHelper {
    private final AdditionalServicesRepository additionalServicesRepository;
    private final Map<String, String> additionalServices = new HashMap<>();

    public AdditionalServicesGenerator(Faker faker, UserRepository userRepository, AdditionalServicesRepository additionalServicesRepository) {
        super(faker, userRepository);
        this.additionalServicesRepository = additionalServicesRepository;
        additionalServices.put("Dostęp do Internetu", "Szybki internet w cenie 30 zł/miesiąc.");
        additionalServices.put("Drukarka", "Drukarka dostępna na terenie akademika za 0,20 zł/strona.");
        additionalServices.put("Obiady", "Miejska stołówka oferuje obiady w cenie 12 zł/posiłek.");
        additionalServices.put("Pralnia", "Samoobsługowa pralnia dostępna za 5 zł/pranie.");
        additionalServices.put("Parking", "Parking dla mieszkańców w cenie 50 zł/miesiąc.");
        additionalServices.put("Siłownia", "Darmowy dostęp do siłowni na terenie akademika.");
        additionalServices.put("Serwis rowerowy", "Bezpłatny serwis i naprawa rowerów.");
        additionalServices.put("Bezpieczeństwo", "Całodobowa ochrona i monitoring.");
        additionalServices.put("Sala TV", "Sala z dostępem do kanałów telewizyjnych.");
        additionalServices.put("Serwis sprzątający", "Codzienny serwis sprzątający pokoi.");
    }

    public void generateAdditionalServices() {
        additionalServices.forEach((name, description) ->
                additionalServicesRepository.save(createAdditionalService(name, description)));
    }

    private AdditionalServices createAdditionalService(String name, String description) {
        return AdditionalServices.builder()
                .name(name)
                .description(description)
                .build();
    }
}
