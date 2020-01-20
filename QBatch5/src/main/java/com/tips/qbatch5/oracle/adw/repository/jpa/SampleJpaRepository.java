package com.tips.qbatch5.oracle.adw.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.qbatch5.oracle.adw.repository.entity.Sample_Han;

@Repository
public interface SampleJpaRepository extends JpaRepository<Sample_Han, Integer> {
}