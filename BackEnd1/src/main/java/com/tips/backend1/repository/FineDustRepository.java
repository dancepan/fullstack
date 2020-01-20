package com.tips.backend1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.backend1.model.entity.FineDust;

@Repository
public interface FineDustRepository extends JpaRepository<FineDust, String>
{
    //List<FineDust> findAll();  // from JPA
    
    @Query(value = "select stationname, mangname, so2value, covalue, o3value, no2value, pm10value from public.fine_dust", nativeQuery = true)
    List<Object[]> findAllByQuery();
}
