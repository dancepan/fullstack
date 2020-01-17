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
import com.tips.restapi.model.json.FineDustStageJson;
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
        
        List<FineDust> fineDustAllByJPA   = this.fineDustRepository.findAll();
        
        // JPA return value
        for(FineDust fineDustEntity : fineDustAllByJPA)  
        {
            FineDustJson fineDustJsonObj = new FineDustJson();
            
            fineDustJsonObj.setDatatime   (fineDustEntity.getDatatime   ());
            fineDustJsonObj.setStationname(fineDustEntity.getStationname());
            fineDustJsonObj.setMangname   (fineDustEntity.getMangname   ());
            fineDustJsonObj.setSo2value   (fineDustEntity.getSo2value   ());
            fineDustJsonObj.setCovalue    (fineDustEntity.getCovalue    ());
            fineDustJsonObj.setO3value    (fineDustEntity.getO3value    ());
            fineDustJsonObj.setNo2value   (fineDustEntity.getNo2value   ());
            fineDustJsonObj.setPm10value  (fineDustEntity.getPm10value  ());
            
            fineDustJsonList.add(fineDustJsonObj);
        }
          
        fineDustJson.setFineDustList(fineDustJsonList);
        
        return fineDustJson;
    }
}
