package com.tips.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.restapi.model.FineDust;

@Repository
public interface FineDustRepository extends JpaRepository<FineDust, String> {}
