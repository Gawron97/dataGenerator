package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMongoRepository extends MongoRepository<Student, String> {

    Boolean existsByEmail(String email);

}
