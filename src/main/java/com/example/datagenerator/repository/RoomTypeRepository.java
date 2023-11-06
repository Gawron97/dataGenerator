package com.example.datagenerator.repository;

import com.example.datagenerator.entity.RoomType;
import com.example.datagenerator.entity.Statute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
