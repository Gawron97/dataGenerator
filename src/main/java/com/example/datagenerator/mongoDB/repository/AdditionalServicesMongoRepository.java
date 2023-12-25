package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.AdditionalServices;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdditionalServicesMongoRepository extends MongoRepository<AdditionalServices, String> {
}
