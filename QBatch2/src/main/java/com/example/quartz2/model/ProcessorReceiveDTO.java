package com.example.quartz2.model;

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
	private String id;
	private String stock;
	private String time;
	private String price;
	private String shares;
}
