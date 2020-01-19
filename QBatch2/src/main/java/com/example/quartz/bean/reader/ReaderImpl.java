package com.example.quartz.bean.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.example.quartz.model.ReaderReturnDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public class ReaderImpl implements ItemReader<ReaderReturnDTO>
{
	private static final Logger log = LoggerFactory.getLogger(ReaderImpl.class);
	
	private static int readCount = 0;
	
	public ReaderImpl() {};
	
	public ReaderReturnDTO getResource()
	{
		log.info("[ReaderImpl] getResource() readCount : " + readCount);
		
		ReaderReturnDTO readerReturnDTO = new ReaderReturnDTO();
		
		readerReturnDTO.setPrice ("1");
		readerReturnDTO.setShares("2");
		readerReturnDTO.setStock ("2");
		readerReturnDTO.setTime  ("4");
		
		return readerReturnDTO;
	}
	
	@Override
	public ReaderReturnDTO read()
	{
		ReaderReturnDTO readerReturnDTO = null;
		
		if (readCount < 10)
		{
			readerReturnDTO = this.getResource();
			
			readCount++;
		}
		
		return readerReturnDTO;
	}
}
