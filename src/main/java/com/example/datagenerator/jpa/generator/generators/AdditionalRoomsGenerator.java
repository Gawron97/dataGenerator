package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.AdditionalRooms;
import com.example.datagenerator.jpa.entity.AdditionalRoomType;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.AdditionalRoomsRepository;
import com.example.datagenerator.jpa.repository.AdditionalRoomTypeRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.List;

public class AdditionalRoomsGenerator extends GeneratorHelper {

    private final AdditionalRoomsRepository additionalRoomsRepository;
    private final List<AdditionalRoomType> additionalRoomTypes;
    private final List<String> descriptions = List.of(
            "Kuchnia wyposażona w sztućce, kubki i talerze oraz czajnik",
            "Sala kominkowa do spotkań i relaksu",
            "Sala fitness z nowoczesnym sprzętem do ćwiczeń",
            "Sala konferencyjna z miejscami siedzącymi dla 50 osób",
            "Sala gier z bilardem, ping-pongiem i grami planszowymi",
            "Pracownia komputerowa z dostępem do internetu",
            "Biblioteka z bogatym zbiorem książek i czasopism",
            "Pralnia samoobsługowa z pralkami i suszarkami",
            "Pomieszczenie do przechowywania rowerów",
            "Kawiarnia z kawą i przekąskami dla mieszkańców"
    );
    public AdditionalRoomsGenerator(Faker faker, UserRepository userRepository, AdditionalRoomsRepository additionalRoomsRepository, AdditionalRoomTypeRepository additionalRoomTypeRepository) {
        super(faker, userRepository);
        this.additionalRoomsRepository = additionalRoomsRepository;
        additionalRoomTypes = additionalRoomTypeRepository.findAll();
    }

    public void generateAdditionalRooms(int numberOfAdditionalRooms) {

        if(additionalRoomTypes.isEmpty()) {
            return;
        }

        for (int i = 0; i < numberOfAdditionalRooms; i++) {
            AdditionalRooms additionalRooms = AdditionalRooms.builder()
                    .roomNumber(faker.number().numberBetween(1, 110))
                    .description(descriptions.get(faker.number().numberBetween(0, descriptions.size())))
                    .additionalRoomType(getRandomAdditionalRoomType())
                    .build();

            additionalRoomsRepository.save(additionalRooms);
        }
    }

    private AdditionalRoomType getRandomAdditionalRoomType() {
        return additionalRoomTypes.get(faker.number().numberBetween(0, additionalRoomTypes.size()));
    }

}
