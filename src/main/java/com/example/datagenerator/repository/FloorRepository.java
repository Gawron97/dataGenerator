package com.example.datagenerator.repository;

import com.example.datagenerator.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findByLevelGreaterThanEqual(Long minLevel);
}
