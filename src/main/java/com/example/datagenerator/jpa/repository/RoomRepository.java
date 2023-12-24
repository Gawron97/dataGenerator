package com.example.datagenerator.jpa.repository;

import com.example.datagenerator.jpa.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT c.room.id FROM Contract c WHERE c.start_date <= :endDate AND c.end_date >= :startDate)")
    List<Room> findRoomsWithoutContractsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
