package com.tips.restapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String  datatime;      //-- 측정일
    
    @Column
    private String  stationname;   //-- 측정소명
    
    @Column
    private String  mangname;      //-- 측정망정보
    
    @Column
    private String  so2value;      //-- 아황산가스 농도
    
    @Column
    private String  covalue;       //-- 일산화탄소 농도
    
    @Column
    private String  o3value;       //-- 오존 농도
    
    @Column
    private String  no2value;      //-- 이산화질소 농도
    
    @Column
    private String  pm10value;     //-- 미세먼지(PM10) 농도
}
