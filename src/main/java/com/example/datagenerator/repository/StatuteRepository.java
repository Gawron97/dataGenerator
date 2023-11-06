package com.example.datagenerator.repository;

import com.example.datagenerator.entity.AdditionalRoomType;
import com.example.datagenerator.entity.Statute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatuteRepository extends JpaRepository<Statute, Long> {
}
