package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
