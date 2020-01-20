package com.tips.qbatch5.oracle.adw.batch.RealEstateTrade.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tips.qbatch5.oracle.adw.repository.entity.Estate_Real_Apt_Trx;
import com.tips.qbatch5.oracle.adw.service.jpa.RealEstateTradeService;

@Configuration
public class RESTRealEstateAptTradeWriter implements ItemWriter<List<Estate_Real_Apt_Trx>> {
    private static final Logger logger = LoggerFactory.getLogger(RESTRealEstateAptTradeWriter.class);

    @Autowired
    RealEstateTradeService realEstateTradeService;

    @Override
    public void write(List<? extends List<Estate_Real_Apt_Trx>> lists) throws Exception {
        logger.debug("Received the information of {} array", lists.size());
        
        for (List<Estate_Real_Apt_Trx> list : lists) {
            logger.info("아파트 실거래가 데이터 수신, ADW 트랜잭션 시작. [거래 건수 : " + list.size() + " 건]");
            realEstateTradeService.saveAptTradeAll(list);
            logger.info("아파트 실거래가 데이터 수신, ADW 트랜잭션 종료. [거래 건수 : " + list.size() + " 건]");
        }
    }
}