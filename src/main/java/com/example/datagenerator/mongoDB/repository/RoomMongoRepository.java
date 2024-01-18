package com.example.datagenerator.mongoDB.repository;

import com.example.datagenerator.mongoDB.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomMongoRepository extends MongoRepository<Room, String> {
}
