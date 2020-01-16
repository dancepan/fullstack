package com.tips.restapi.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.restapi.model.entity.FineDust;
import com.tips.restapi.model.json.FineDustJson;
import com.tips.restapi.repository.FineDustRepository;


@Transactional
@Service
public class FineDustServiceImpl implements FineDustService
{
    @Autowired
    FineDustRepository fineDustRepository;

    public FineDustJson findFineDustJson() throws InvalidParameterException 
    {
        FineDustJson fineDustJson = new FineDustJson();
        
        // Retune value
        ArrayList <FineDustJson> fineDustJsonList = new ArrayList<FineDustJson>();
        
        List<FineDust> fineDustAllByJPA   = this.fineDustRepository.findAll()       ;  // 1. findAll by JPA
        List<Object[]> fineDustAllByQuery = this.fineDustRepository.findAllByQuery();  // 2. findAll by Query
        
        // JPA return value
        for(FineDust fineDustEntity : fineDustAllByJPA)  
        {
            FineDustJson fineDustJsonObj = new FineDustJson();
            
            fineDustJsonObj.setDatatime   (fineDustEntity.getDatatime());      //-- 측정일
            fineDustJsonObj.setStationname(fineDustEntity.getStationname());   //-- 측정소명
            fineDustJsonObj.setMangname   (fineDustEntity.getMangname());      //-- 측정망정보
            fineDustJsonObj.setSo2value   (fineDustEntity.getSo2value());      //-- 아황산가스 농도
            fineDustJsonObj.setCovalue    (fineDustEntity.getCovalue());       //-- 일산화탄소 농도
            fineDustJsonObj.setO3value    (fineDustEntity.getO3value());       //-- 오존 농도
            fineDustJsonObj.setNo2value   (fineDustEntity.getNo2value());      //-- 이산화질소 농도
            fineDustJsonObj.setPm10value  (fineDustEntity.getPm10value());     //-- 미세먼지(PM10) 농도
            
            fineDustJsonList.add(fineDustJsonObj);
        }
        
        // Query return value
        for(Object [] fineDustEntity : fineDustAllByQuery)  
        {
            FineDustJson fineDustJsonObj = new FineDustJson();
              
            fineDustJsonObj.setStationname((String)fineDustEntity[0]);   //-- 측정소명
            fineDustJsonObj.setMangname   ((String)fineDustEntity[1]);   //-- 측정망정보
            fineDustJsonObj.setSo2value   ((String)fineDustEntity[2]);   //-- 아황산가스 농도
            fineDustJsonObj.setCovalue    ((String)fineDustEntity[3]);   //-- 일산화탄소 농도
            fineDustJsonObj.setO3value    ((String)fineDustEntity[4]);   //-- 오존 농도
            fineDustJsonObj.setNo2value   ((String)fineDustEntity[5]);   //-- 이산화질소 농도
            fineDustJsonObj.setPm10value  ((String)fineDustEntity[6]);   //-- 미세먼지(PM10) 농도
              
            fineDustJsonList.add(fineDustJsonObj);
        }
          
        fineDustJson.setFineDustList(fineDustJsonList);
        
        return fineDustJson;
    }
}
