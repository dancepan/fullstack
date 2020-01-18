package com.tips.batch.step;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tips.batch.model.TableOne;
import com.tips.batch.model.json.ReaderItemJson;
import com.tips.batch.service.TableOneService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class Writer implements ItemWriter<List<TableOne>>
{
	private static final Logger logger = LoggerFactory.getLogger(Processor.class);
	
    @Autowired
    TableOneService tableOneService;
    
    @Override
    public void write(List<? extends List<TableOne>> lists) throws Exception
    {
    	
    	
    	
        for (List<TableOne> list : lists) {
            logger.info("아파트 실거래가 데이터 수신, ADW 트랜잭션 시작. [거래 건수 : " + list.size() + " 건]");
            
            tableOneService.saveTableOneAllList(list);
            
            logger.info("아파트 실거래가 데이터 수신, ADW 트랜잭션 종료. [거래 건수 : " + list.size() + " 건]");
        }
    	
  	
    	
        System.out.println("------------ writer----------------");

    }

}
