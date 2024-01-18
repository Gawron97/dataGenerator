package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.OfficeWorker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfficeWorkerMongoRepository extends MongoRepository<OfficeWorker, String> {
}
