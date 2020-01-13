package com.tips.restapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tips.restapi.model.CarModel;

public interface CarRepository extends MongoRepository<CarModel, String> { }
