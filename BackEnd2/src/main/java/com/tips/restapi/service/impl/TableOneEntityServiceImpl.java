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
            
            TableOneJsonObj.setId            (TableOneEntity.getId         ());
            TableOneJsonObj.setReturntype    (TableOneEntity.getDatatime   ());
            TableOneJsonObj.setCograde       (TableOneEntity.getCograde    ());
            TableOneJsonObj.setCovalue       (TableOneEntity.getCovalue    ());
            TableOneJsonObj.setDataterm      (TableOneEntity.getDataterm   ());
            TableOneJsonObj.setDatatime      (TableOneEntity.getDatatime   ());
            TableOneJsonObj.setKhaigrade     (TableOneEntity.getKhaigrade  ());
            TableOneJsonObj.setKhaivalue     (TableOneEntity.getKhaivalue  ());
            TableOneJsonObj.setMangname      (TableOneEntity.getMangname   ());
            TableOneJsonObj.setNo2grade      (TableOneEntity.getNo2grade   ());
            TableOneJsonObj.setNo2value      (TableOneEntity.getNo2value   ());
            TableOneJsonObj.setNumofrows     (TableOneEntity.getNumofrows  ());
            TableOneJsonObj.setO3grade       (TableOneEntity.getO3grade    ());
            TableOneJsonObj.setO3value       (TableOneEntity.getO3value    ());
            TableOneJsonObj.setPageno        (TableOneEntity.getPageno     ());
            TableOneJsonObj.setPm10grade     (TableOneEntity.getPm10grade  ());
            TableOneJsonObj.setPm10grade1h   (TableOneEntity.getPm10grade1h());
            TableOneJsonObj.setPm10value     (TableOneEntity.getPm10value  ());
            TableOneJsonObj.setPm10value24   (TableOneEntity.getPm10value24());
            TableOneJsonObj.setPm25grade     (TableOneEntity.getPm25grade  ());
            TableOneJsonObj.setPm25grade1h   (TableOneEntity.getPm25grade1h());
            TableOneJsonObj.setPm25value     (TableOneEntity.getPm25value  ());
            TableOneJsonObj.setPm25value24   (TableOneEntity.getPm25value24());
            TableOneJsonObj.setResultcode    (TableOneEntity.getResultcode ());
            TableOneJsonObj.setResultmsg     (TableOneEntity.getResultmsg  ());
            TableOneJsonObj.setRnum          (TableOneEntity.getRnum       ());
            TableOneJsonObj.setServicekey    (TableOneEntity.getServicekey ());
            TableOneJsonObj.setSidoname      (TableOneEntity.getSidoname   ());
            TableOneJsonObj.setSo2grade      (TableOneEntity.getSo2grade   ());
            TableOneJsonObj.setSo2value      (TableOneEntity.getSo2value   ());
            TableOneJsonObj.setStationcode   (TableOneEntity.getStationcode());
            TableOneJsonObj.setStationname   (TableOneEntity.getStationname());
            TableOneJsonObj.setTotalcount    (TableOneEntity.getTotalcount ());
            TableOneJsonObj.setVer           (TableOneEntity.getVer        ());
            
            TableOneJsonList.add(TableOneJsonObj);
        }
          
        TableOneJson.setTableOneList(TableOneJsonList);
        
        return TableOneJson;
    }
}
