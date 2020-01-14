package com.tips.batch.step;

import java.text.ParseException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import lombok.Data;


public class Reader implements ItemReader<String> {
	private String[] messages = { "javainuse.com",
			"Welcome to Spring Batch Example",
			"We use H2 Database for this example" };

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		
		String uri = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D&numOfRows=100&pageNo=1&sidoName=%EC%9D%B8%EC%B2%9C&ver=1.3&_returnType=json";
		
		RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.getForObject(uri, );
		
		return null;
	}

}
