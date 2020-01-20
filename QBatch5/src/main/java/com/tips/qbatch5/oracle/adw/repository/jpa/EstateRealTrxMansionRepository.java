package com.tips.qbatch5.oracle.adw.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tips.qbatch5.oracle.adw.repository.entity.Estate_Real_Mansion_Trx;
import com.tips.qbatch5.oracle.adw.repository.entity.Estate_Real_Mansion_Trx_Keys;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Repository
public interface EstateRealTrxMansionRepository extends JpaRepository<Estate_Real_Mansion_Trx, Estate_Real_Mansion_Trx_Keys> {
    //@Modifying
    //@Query("delete from Estate_Real_Trx_Apt e where e.계약월 = :month and e.지역코드 = :locationCode")
    //void deleteAllById(@Param("month") String month, @Param("locationCode") String locationCode);
}