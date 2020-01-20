package com.example.quartz2.bean.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.example.quartz2.model.ReaderReturnDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public class ReaderRestApiImpl implements ItemReader<ReaderReturnDTO>
{
	private static final Logger log = LoggerFactory.getLogger(ReaderRestApiImpl.class);
	
	private static int readCount = 0;
	
	public ReaderRestApiImpl() {};
	
	public ReaderReturnDTO getResource(int readCount)
	{
		log.info("[ReaderImpl] getResource() readCount : " + readCount);
		
		ReaderReturnDTO readerReturnDTO = new ReaderReturnDTO();
		
		readerReturnDTO.setId    ( String.valueOf(readCount));
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
			readerReturnDTO = this.getResource(readCount);
			
			readCount++;
		}
		
		return readerReturnDTO;
	}
}
