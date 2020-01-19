package com.example.quartz2.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class BatchTarget implements Serializable
{
	@Id
    @Column private String column1;    
    @Column private String column2;
    @Column private String column3;
    @Column private String column4;
    @Column private String column5;
}