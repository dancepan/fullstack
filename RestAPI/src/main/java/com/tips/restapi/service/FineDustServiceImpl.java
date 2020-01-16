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

//<<<<<<< HEAD
//    public FineDustJson fineDustJson() throws InvalidParameterException
//=======
    public FineDustJson findFineDustJson() throws InvalidParameterException 
//>>>>>>> branch 'leehyounkyoo' of https://github.com/minjeongkoo/mvpTips
    {
//<<<<<<< HEAD
//        FineDustJson fineDustList = new FineDustJson();
//=======
        FineDustJson fineDustJson = new FineDustJson();
//>>>>>>> branch 'leehyounkyoo' of https://github.com/minjeongkoo/mvpTips

//<<<<<<< HEAD
//        ArrayList <FineDustJson> fineDustList1 = new ArrayList<FineDustJson>();
//=======
        ArrayList <FineDustJson> fineDustJsonList = new ArrayList<FineDustJson>();
//>>>>>>> branch 'leehyounkyoo' of https://github.com/minjeongkoo/mvpTips
        
        // JPA 메소를 이용하여 리스트로 받아 처리한다.
        List<FineDust> fineDustEntityList = this.fineDustRepository.findAll();
        
        // @Query annotaion 을 이용한 repository 생성 예정
        // 이럴경우 LIST<Object[]> 행태로 받고 service 에서 Json 클래스로 변환해 주어야 함.
        
        // 1. List 형태로 받을 경우 아래와 같이 처리
        // 2. @Query 결과로 받을 경우 Object를 클래스화 하는 로직 필요(구현예정)
        for(FineDust fineDustEntity : fineDustEntityList)
        {
//<<<<<<< HEAD
//            FineDustJson FineDustTemp = new FineDustJson();
//=======
            FineDustJson fineDustJsonObj = new FineDustJson();
//>>>>>>> branch 'leehyounkyoo' of https://github.com/minjeongkoo/mvpTips
            
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
        
        fineDustJson.setFineDustList(fineDustJsonList);
        
        return fineDustJson;
    }
}
