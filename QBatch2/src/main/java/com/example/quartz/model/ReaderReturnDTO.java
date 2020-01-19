package com.example.quartz.model;

import lombok.Data;

/**
 * The Class FxMarketEvent.
 * 
 * @author ashraf
 */
@Data
public class ReaderReturnDTO 
{
	private String id;
    private String stock;
    private String time;
    private String price;
    private String shares;
}
