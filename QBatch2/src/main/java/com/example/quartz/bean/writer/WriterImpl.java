package com.example.quartz.bean.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.quartz.model.ProcessorReceiveDTO;
import com.example.quartz.model.entity.BatchTarget;
import com.example.quartz.repository.BatchTargetRepository;
import com.example.quartz.service.BatchTargetService;

import com.example.quartz.model.BizVO;
import com.example.quartz.model.FileWriteDTO;

/**
 * The Class StockPriceAggregator.
 * 
 * @author ashraf
 */
public class WriterImpl implements ItemWriter<ProcessorReceiveDTO>
{
	private static final Logger log = LoggerFactory.getLogger(WriterImpl.class);
	
    @Autowired
    private BizVO bizVO;

    BatchTargetService batchTargetService;
    BatchTargetRepository batchTargetRepository;

    @Override
    public void write(List<? extends ProcessorReceiveDTO> trades) throws Exception
    {
    	log.info("[WriterImpl] write() trades : " + trades.toString());
    	
        // Transfer to VO
        trades.forEach(t ->
        {
            if (bizVO.containsKey(t.getStock())) 
            {
                String tradePrice = t.getPrice();
                
                FileWriteDTO priceDetails = bizVO.get(t.getStock());
                
//                // Set highest price
//                if (tradePrice > priceDetails.getHigh())
//                {
//                    priceDetails.setHigh(tradePrice);
//                }
//                
//                // Set lowest price
//                if (tradePrice < priceDetails.getLow())
//                {
//                    priceDetails.setLow(tradePrice);
//                }
//                
//                // Set close price
//                priceDetails.setClose(tradePrice);
                
                bizVO.put(t.getStock(), 
                    new FileWriteDTO(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
            }
            else
            {
            	bizVO.put(t.getStock(),
                    new FileWriteDTO(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
            }
        });
    }
}
