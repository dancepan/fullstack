package com.example.quartz.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.quartz.etlprocess.FxMarketPricesStore;
import com.example.quartz.etlprocess.StockPriceDetails;
import com.example.quartz.model.TradeDTO;

/**
 * The Class StockPriceAggregator.
 * 
 * @author ashraf
 */
public class WriterImpl implements ItemWriter<TradeDTO>
{
    @Autowired
    private FxMarketPricesStore fxMarketPricesStore;

    private static final Logger log = LoggerFactory.getLogger(WriterImpl.class);

    @Override
    public void write(List<? extends TradeDTO> trades) throws Exception
    {

        

        
        trades.forEach(t -> {
            if (fxMarketPricesStore.containsKey(t.getStock())) 
            {
                double tradePrice = t.getPrice();
                
                StockPriceDetails priceDetails = fxMarketPricesStore.get(t.getStock());
                
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
                
                log.info("Adding 111111 new stock {}", t.getStock());
                fxMarketPricesStore.put(t.getStock(),
                        new StockPriceDetails(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
            }
            else
            {
                log.info("Adding 2222222222 new stock {}", t.getStock());
                
                fxMarketPricesStore.put(t.getStock(),
                        new StockPriceDetails(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
            }
        });
    }
}
