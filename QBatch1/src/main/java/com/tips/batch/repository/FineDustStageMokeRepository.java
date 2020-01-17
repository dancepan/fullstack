package com.tips.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.batch.entity.FineDust;
import com.tips.batch.entity.FineDustStageMock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Repository
public interface FineDustStageMokeRepository extends JpaRepository<FineDustStageMock, String>
{

    public void saveAll(List<FineDustStageMock> fineDustStageMock);

}
