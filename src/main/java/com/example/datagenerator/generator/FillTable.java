package com.example.datagenerator.generator;

import com.example.datagenerator.generator.generators.*;
import com.example.datagenerator.repository.*;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillTable {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final SecurityRepository securityRepository;
    private final OfficeWorkerRepository officeWorkerRepository;
    private final AdditionalRoomTypeRepository additionalRoomTypeRepository;
    private final AdditionalRoomsRepository additionalRoomsRepository;
    private final ComplainRepository complainRepository;
    private final ModuleTypeRepository moduleTypeRepository;
    private final ModuleRepository moduleRepository;

    private final FacultyRepository facultyRepository;

    private final StatuteRepository statuteRepository;
    private final RoomTypeRepository roomTypeRepository;

    private final DormitoryRepository dormitoryRepository;

    public TableGeneratorApi fillTable() {
        Faker faker = new Faker();
        return new TableGeneratorApi(new UserGenerator(faker, userRepository),
                new ManagerWorkerGenerator(faker, managerRepository, userRepository),
                new OfficeWorkerGenerator(faker, officeWorkerRepository, userRepository),
                new SecurityWorkerGenerator(faker, securityRepository, userRepository),
                new StudentGenerator(faker, studentRepository, userRepository),
                new AdditionalRoomTypeGenerator(faker, userRepository, additionalRoomTypeRepository),
                new AdditionalRoomsGenerator(faker, userRepository, additionalRoomsRepository, additionalRoomTypeRepository),
                new ComplainGenerator(faker, userRepository, complainRepository, studentRepository),
                new ModuleTypeGenerator(faker, userRepository, moduleTypeRepository),
                new ModuleGenerator(faker, userRepository, moduleRepository, moduleTypeRepository),
                new FacultyGenerator(faker, userRepository, facultyRepository),
                new StatuteGenerator(faker,userRepository,statuteRepository),
                new RoomTypeGenerator(faker,userRepository,roomTypeRepository),
                new DormitoryGenerator(faker, userRepository,dormitoryRepository,managerRepository,statuteRepository)
        );

    }
}
