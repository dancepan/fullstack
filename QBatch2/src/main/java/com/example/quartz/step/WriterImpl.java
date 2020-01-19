package com.example.quartz.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.quartz.model.ProcessorReceiveDTO;
import com.example.quartz.model.BizVO;
import com.example.quartz.model.FileWriteDTO;

/**
 * The Class StockPriceAggregator.
 * 
 * @author ashraf
 */
public class WriterImpl implements ItemWriter<ProcessorReceiveDTO>
{
    @Autowired
    private BizVO bizVO;

    private static final Logger log = LoggerFactory.getLogger(WriterImpl.class);

    @Override
    public void write(List<? extends ProcessorReceiveDTO> trades) throws Exception
    {
    	log.info("[WriterImpl] write() trades : " + trades.toString());
    	
        trades.forEach(t ->
        {
            if (bizVO.containsKey(t.getStock())) 
            {
                double tradePrice = t.getPrice();
                
                FileWriteDTO priceDetails = bizVO.get(t.getStock());
                
                // Set highest price
                if (tradePrice > priceDetails.getHigh())
                {
                    priceDetails.setHigh(tradePrice);
                }
                
                // Set lowest price
                if (tradePrice < priceDetails.getLow())
                {
                    priceDetails.setLow(tradePrice);
                }
                
                // Set close price
                priceDetails.setClose(tradePrice);
                
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
