package com.tips.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.restapi.model.TableOne;

@Repository
public interface TableOneEntityRepository extends JpaRepository<TableOne, String>
{
    //List<FineDust> findAll();  // from JPA
}
