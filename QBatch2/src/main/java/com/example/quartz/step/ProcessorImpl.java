package com.example.quartz.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.quartz.model.MarketEventDTO;
import com.example.quartz.model.TradeDTO;

/**
 * The Class FxMarketEventProcessor.
 * 
 * @author ashraf
 */
public class ProcessorImpl implements ItemProcessor<MarketEventDTO, TradeDTO>
{
    private static final Logger log = LoggerFactory.getLogger(ProcessorImpl.class);

    @Override
    public TradeDTO process(final MarketEventDTO fxMarketEvent) throws Exception
    {
        final String    stock  =                fxMarketEvent.getStock () ;
        final String    time   =                fxMarketEvent.getTime  () ;
        final double    price  = Double.valueOf(fxMarketEvent.getPrice ());
        final long      shares = Long.valueOf  (fxMarketEvent.getShares());

        final TradeDTO  trade  = new TradeDTO(stock, time, price, shares);

        log.info("[ProcessorImpl] Converting (" + fxMarketEvent + ") into (" + trade + ")");

        return trade;
    }
}
