package com.example.quartz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

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
