package com.example.quartz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Class StockPriceDetails.
 *
 * @author ashraf
 */
@Data
@AllArgsConstructor
public class FileWriteDTO
{
	private String stock;
	private double open;
	private double close;
	private double low;
	private double high;
}
