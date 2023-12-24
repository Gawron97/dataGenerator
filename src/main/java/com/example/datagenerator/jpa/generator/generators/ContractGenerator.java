package com.example.datagenerator.jpa.generator.generators;

import com.example.datagenerator.jpa.entity.Contract;
import com.example.datagenerator.jpa.entity.Room;
import com.example.datagenerator.jpa.entity.Student;
import com.example.datagenerator.jpa.generator.GeneratorHelper;
import com.example.datagenerator.jpa.repository.ContractRepository;
import com.example.datagenerator.jpa.repository.RoomRepository;
import com.example.datagenerator.jpa.repository.StudentRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ContractGenerator extends GeneratorHelper {

    private final ContractRepository contractRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public ContractGenerator(Faker faker, UserRepository userRepository, ContractRepository contractRepository,
                             StudentRepository studentRepository, RoomRepository roomRepository){
        super(faker, userRepository);
        this.contractRepository = contractRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    public void generateContracts(LocalDate periodStartDate, LocalDate periodEndDate, int numberOfContracts){

        List<Room> freeRooms = this.roomRepository.findRoomsWithoutContractsInPeriod(periodStartDate,periodEndDate);
        freeRooms = freeRooms.stream().filter(Room::getIsAvailable).collect(Collectors.toList());
        List<Student> studentsWithoutRooms = this.studentRepository.findStudentsWithoutContractsInPeriod(periodStartDate,periodEndDate);

        for(int i = 0; i < numberOfContracts; i++){
            if (!studentsWithoutRooms.isEmpty()) {
                Student currentStudent = studentsWithoutRooms.get(faker.number().numberBetween(0,studentsWithoutRooms.size()));
                Room currentRoom = freeRooms.get(faker.number().numberBetween(0,freeRooms.size()));

                createContract(currentStudent, currentRoom, periodStartDate, periodEndDate);
                if(currentRoom.getFreeBeds() != null) {
                    Long freeBeds = currentRoom.getFreeBeds();

                    currentRoom.setFreeBeds(currentRoom.getFreeBeds() - 1);
                    if (freeBeds == 0) {
                        currentRoom.setIsAvailable(false);
                        freeRooms.remove(currentRoom);
                    }
                }

                studentsWithoutRooms.remove(currentStudent);
            }
        }


    }


    private void createContract(Student student, Room room, LocalDate startDate, LocalDate endDate){

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be greater than or equal to start date");
        }


        Contract contract = Contract.builder()
                .start_date(startDate)
                .end_date(endDate)
                .room(room)
                .student(student)
                .build();

        contractRepository.save(contract);

    }


}
