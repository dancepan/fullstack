package com.example.quartz.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.example.quartz.model.ReaderReturnDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public class ReaderImpl2 implements ItemReader<ReaderReturnDTO>
{
	private static final Logger log = LoggerFactory.getLogger(ReaderImpl2.class);
	
	ReaderReturnDTO readerReturnDTO = new ReaderReturnDTO();
	
	public ReaderImpl2() {};
	
	@Override
	public ReaderReturnDTO read()
	{
		log.info("[ReaderImpl] read()");
		
		readerReturnDTO.setPrice("1");
		readerReturnDTO.setShares("2");
		readerReturnDTO.setStock("2");
		readerReturnDTO.setTime("4");
		
		return readerReturnDTO;
	}
}
