package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, Long> {
}
