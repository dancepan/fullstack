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

    public FineDustJson fineDustJson() throws InvalidParameterException
    {
        FineDustJson fineDustList = new FineDustJson();

        ArrayList <FineDustJson> fineDustList1 = new ArrayList<FineDustJson>();
        List<FineDust> fineDustEntityList = this.fineDustRepository.findAll();
        
        for(FineDust fineDustEntity : fineDustEntityList)
        {
            FineDustJson FineDustTemp = new FineDustJson();
            
            FineDustTemp.setCovalue(fineDustEntity.getCovalue());
            
            FineDustTemp.setDatatime   (fineDustEntity.getDatatime());      //-- ������
            FineDustTemp.setStationname(fineDustEntity.getStationname());   //-- �����Ҹ�
            FineDustTemp.setMangname   (fineDustEntity.getMangname());      //-- ����������
            FineDustTemp.setSo2value   (fineDustEntity.getSo2value());      //-- ��Ȳ�갡�� ��
            FineDustTemp.setCovalue    (fineDustEntity.getCovalue());       //-- �ϻ�ȭź�� ��
            FineDustTemp.setO3value    (fineDustEntity.getO3value());       //-- ���� ��
            FineDustTemp.setNo2value   (fineDustEntity.getNo2value());      //-- �̻�ȭ���� ��
            FineDustTemp.setPm10value  (fineDustEntity.getPm10value());     //-- �̼�����(PM10) ��
            
            
            fineDustList1.add(FineDustTemp);
        }
        
        fineDustList.setFineDustList(fineDustList1);
        
        return fineDustList;
    }
}
