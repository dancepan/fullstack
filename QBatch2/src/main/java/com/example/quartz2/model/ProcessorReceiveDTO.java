package com.example.quartz2.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessorReceiveDTO
{
	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;
}
