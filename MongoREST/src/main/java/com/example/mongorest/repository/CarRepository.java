package com.example.mongorest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongorest.model.CarModel;

public interface CarRepository extends MongoRepository<CarModel, String> { }
