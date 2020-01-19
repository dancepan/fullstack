package com.example.quartz.step;

import org.springframework.batch.item.ItemReader;

import com.example.quartz.model.ReaderReturnDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public class ReaderImpl2 implements ItemReader<ReaderReturnDTO>
{
	public ReaderImpl2() {};
	
	@Override
	public ReaderReturnDTO read()
	{
		return null;
	}
}
