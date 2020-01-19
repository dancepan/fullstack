package com.tips.qbatch1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.model.json.ReaderItemJson;


@Transactional
@Repository
public interface TableOneRepository extends JpaRepository<TableOne, String>
{
}
