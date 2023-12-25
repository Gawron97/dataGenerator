package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.Dormitory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DormitoryMongoRepository extends MongoRepository<Dormitory, String> {
}
