package com.example.quartz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Trade.
 * 
 * @author ashraf
 */
@Data
@AllArgsConstructor
public class ProcessorReceiveDTO
{
	private String stock;
	private String time;
	private double price;
	private long   shares;
}
