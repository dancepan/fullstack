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

        ArrayList <FineDustJson> fineDustJsonList = new ArrayList<FineDustJson>();
        List<FineDust> fineDustEntityList = this.fineDustRepository.findAll();
        
        for(FineDust fineDustEntity : fineDustEntityList)
        {
            FineDustJson fineDustJsonObj = new FineDustJson();
            
            fineDustJsonObj.setDatatime   (fineDustEntity.getDatatime());      //-- ������
            fineDustJsonObj.setStationname(fineDustEntity.getStationname());   //-- �����Ҹ�
            fineDustJsonObj.setMangname   (fineDustEntity.getMangname());      //-- ����������
            fineDustJsonObj.setSo2value   (fineDustEntity.getSo2value());      //-- ��Ȳ�갡�� ��
            fineDustJsonObj.setCovalue    (fineDustEntity.getCovalue());       //-- �ϻ�ȭź�� ��
            fineDustJsonObj.setO3value    (fineDustEntity.getO3value());       //-- ���� ��
            fineDustJsonObj.setNo2value   (fineDustEntity.getNo2value());      //-- �̻�ȭ���� ��
            fineDustJsonObj.setPm10value  (fineDustEntity.getPm10value());     //-- �̼�����(PM10) ��
            
            fineDustJsonList.add(fineDustJsonObj);
        }
        
        fineDustJson.setFineDustList(fineDustJsonList);
        
        return fineDustJson;
    }
}
