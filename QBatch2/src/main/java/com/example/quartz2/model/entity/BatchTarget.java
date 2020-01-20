package com.example.quartz2.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.repository.Temporal;

import lombok.Data;

@Data
@Entity
@Table
public class BatchTarget implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private String id; 

    @Column private String column1;    
    @Column private String column2;
    @Column private String column3;
    @Column private String column4;
    @Column private String column5;
    
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createData;
}
    