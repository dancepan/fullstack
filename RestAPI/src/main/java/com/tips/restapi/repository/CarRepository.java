package com.tips.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.restapi.model.CarModel;

@Repository
public interface CarRepository extends JpaRepository<CarModel, String> {
}
