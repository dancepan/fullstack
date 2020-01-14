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


@Getter
@NoArgsConstructor
@Entity
@Table
public class CarModel implements Serializable {
//	  private String id; 
    @Id
    @Column
    @GeneratedValue
    private Long idx;
    
    @Column
	  private String maker;
    
    @Column
	  private String model;
	
    @Column
	  private String description;
    
    @Builder
    public CarModel(String maker, String model, String description) {
        this.maker = maker;
        this.model = model;
        this.description = description;
    }
    
    public CarModel(){}
}
