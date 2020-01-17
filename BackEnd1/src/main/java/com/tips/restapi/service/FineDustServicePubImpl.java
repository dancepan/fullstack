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
import com.tips.restapi.repository.FineDustPubRepository;
import com.tips.restapi.repository.FineDustRepository;


@Transactional
@Service
public class FineDustServicePubImpl implements FineDustServicePub
{
    @Autowired
    FineDustPubRepository fineDustPubRepository;

    public FineDustStageJson findFineDustStageJson() throws InvalidParameterException 
    {
        FineDustStageJson fineDustStageJson = new FineDustStageJson();
        
        // Retune value
        ArrayList <FineDustStageJson> fineDustStageJsonList = new ArrayList<FineDustStageJson>();
        
        List<Object[]> fineDustStageAllByQuery = this.fineDustPubRepository.findAllByQuery();  // 2. findAll by Query
        
        // Query return value
        for(Object [] fineDustStageEntity : fineDustStageAllByQuery)  
        {
            FineDustStageJson fineDustStageJsonObj = new FineDustStageJson();
              
            fineDustStageJsonObj.setStationname((String)fineDustStageEntity[0]);
            fineDustStageJsonObj.setMangname   ((String)fineDustStageEntity[1]);
            fineDustStageJsonObj.setSo2value   ((String)fineDustStageEntity[2]);
            fineDustStageJsonObj.setCovalue    ((String)fineDustStageEntity[3]);
            fineDustStageJsonObj.setO3value    ((String)fineDustStageEntity[4]);
            fineDustStageJsonObj.setNo2value   ((String)fineDustStageEntity[5]);
            fineDustStageJsonObj.setPm10value  ((String)fineDustStageEntity[6]);
              
            fineDustStageJsonList.add(fineDustStageJsonObj);
        }
          
        fineDustStageJson.setFineDustList(fineDustStageJsonList);
        
        return fineDustStageJson;
    }
}
