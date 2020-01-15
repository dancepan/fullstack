package com.tips.restapi.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.restapi.model.entity.FineDust;
import com.tips.restapi.model.json.FineDustList;
import com.tips.restapi.repository.FineDustRepository;

@Transactional
@Service
public class FineDustServiceImpl implements FineDustService
{
    @Autowired
    FineDustRepository fineDustRepository;

    public FineDustList findFineDustList() throws InvalidParameterException 
    {
        FineDustList fineDustList = new FineDustList();

        ArrayList <FineDustList> fineDustList1 = new ArrayList<FineDustList>();
        List<FineDust> fineDustEntityList = this.fineDustRepository.findAll();
        
        for(FineDust fineDustEntity : fineDustEntityList)
        {
            FineDustList FineDustTemp = new FineDustList();
            
            FineDustTemp.setCovalue(fineDustEntity.getCovalue());
            
            FineDustTemp.setDatatime   (fineDustEntity.getDatatime());      //-- 측정일
            FineDustTemp.setStationname(fineDustEntity.getStationname());   //-- 측정소명
            FineDustTemp.setMangname   (fineDustEntity.getMangname());      //-- 측정망정보
            FineDustTemp.setSo2value   (fineDustEntity.getSo2value());      //-- 아황산가스 농도
            FineDustTemp.setCovalue    (fineDustEntity.getCovalue());       //-- 일산화탄소 농도
            FineDustTemp.setO3value    (fineDustEntity.getO3value());       //-- 오존 농도
            FineDustTemp.setNo2value   (fineDustEntity.getNo2value());      //-- 이산화질소 농도
            FineDustTemp.setPm10value  (fineDustEntity.getPm10value());     //-- 미세먼지(PM10) 농도
            
            
            fineDustList1.add(FineDustTemp);
        }
        
        fineDustList.setFineDustList(fineDustList1);
        
        return fineDustList;
    }
}
