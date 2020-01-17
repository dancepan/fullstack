package com.tips.restapi.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.restapi.model.TableOne;
import com.tips.restapi.model.json.TableOneJson;
import com.tips.restapi.repository.TableOneEntityRepository;
import com.tips.restapi.service.TableOneEntityService;


@Transactional
@Service
public class TableOneEntityServiceImpl implements TableOneEntityService
{
    @Autowired
    TableOneEntityRepository TableOneRepository;

    @Override
    public TableOneJson findTableOneEntity() throws InvalidParameterException 
    {
        TableOneJson TableOneJson = new TableOneJson();
        
        // Retune value
        ArrayList <TableOneJson> TableOneJsonList = new ArrayList<TableOneJson>();
        
        List<TableOne> TableOneAllByJPA = this.TableOneRepository.findAll();
        
        // JPA return value
        for(TableOne TableOneEntity : TableOneAllByJPA)  
        {
            TableOneJson TableOneJsonObj = new TableOneJson();
            
            TableOneJsonObj.setDatatime   (TableOneEntity.getDatatime   ());
            TableOneJsonObj.setStationname(TableOneEntity.getStationname());
            TableOneJsonObj.setMangname   (TableOneEntity.getMangname   ());
            TableOneJsonObj.setSo2value   (TableOneEntity.getSo2value   ());
            TableOneJsonObj.setCovalue    (TableOneEntity.getCovalue    ());
            TableOneJsonObj.setO3value    (TableOneEntity.getO3value    ());
            TableOneJsonObj.setNo2value   (TableOneEntity.getNo2value   ());
            TableOneJsonObj.setPm10value  (TableOneEntity.getPm10value  ());
            
            TableOneJsonList.add(TableOneJsonObj);
        }
          
        TableOneJson.setTableOneList(TableOneJsonList);
        
        return TableOneJson;
    }
}
