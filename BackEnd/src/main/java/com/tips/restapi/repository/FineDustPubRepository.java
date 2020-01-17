package com.tips.restapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.restapi.model.entity.FineDust;


@Repository
public interface FineDustPubRepository extends JpaRepository<FineDust, String>
{
    //List<FineDust> findAll();  // from JPA
    
    @Query(value = "select _returntype,cograde,covalue,dataterm,datatime,khaigrade,khaivalue,mangname,no2grade,\r\n" + 
            "no2value,numofrows,o3grade,o3value,pageno,pm10grade,pm10grade1h,pm10value,\r\n" + 
            "pm10value24,pm25grade,pm25grade1h,pm25value,pm25value24,resultcode,resultmsg,rnum,\r\n" + 
            "servicekey,sidoname,so2grade,so2value,stationcode,stationname,totalcount,ver from public.fine_dust_stage", nativeQuery = true)
    List<Object[]> findAllByQuery();
}


