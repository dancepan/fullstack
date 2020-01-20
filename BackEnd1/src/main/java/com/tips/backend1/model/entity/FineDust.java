package com.tips.backend1.model.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Data
@Entity
@Table
public class FineDust implements Serializable
{
    @Id
    @Column
    private String  datatime;      //-- ������
    
    @Column
    private String  stationname;   //-- �����Ҹ�
    
    @Column
    private String  mangname;      //-- ����������
    
    @Column
    private String  so2value;      //-- ��Ȳ�갡�� ��
    
    @Column
    private String  covalue;       //-- �ϻ�ȭź�� ��
    
    @Column
    private String  o3value;       //-- ���� ��
    
    @Column
    private String  no2value;      //-- �̻�ȭ���� ��
    
    @Column
    private String  pm10value;     //-- �̼�����(PM10) ��
}
