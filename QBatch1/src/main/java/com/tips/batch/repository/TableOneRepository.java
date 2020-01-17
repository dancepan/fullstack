package com.tips.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tips.batch.model.TableOne;
import com.tips.batch.model.json.ReaderItemJson;
import com.tips.batch.model.json.ReaderItemJson;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Repository
public interface TableOneRepository extends JpaRepository<TableOne, String>
{
}
