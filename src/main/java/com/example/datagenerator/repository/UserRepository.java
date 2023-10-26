package com.example.datagenerator.repository;

import com.example.datagenerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
