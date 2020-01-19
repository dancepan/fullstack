package com.example.quartz2.bean.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.quartz2.model.ProcessorReceiveDTO;
import com.example.quartz2.model.ReaderReturnDTO;

/**
 * The Class FxMarketEventProcessor.
 * 
 * @author ashraf
 */
public class ProcessorImpl implements ItemProcessor<ReaderReturnDTO, ProcessorReceiveDTO>
{
    private static final Logger log = LoggerFactory.getLogger(ProcessorImpl.class);

    @Override
    public ProcessorReceiveDTO process(final ReaderReturnDTO fxMarketEvent) throws Exception
    {
    	final String    id     =                fxMarketEvent.getId()     ; 
        final String    stock  =                fxMarketEvent.getStock () ;
        final String    time   =                fxMarketEvent.getTime  () ;
        final String    price  =                fxMarketEvent.getPrice () ;
        final String    shares =                fxMarketEvent.getShares() ;
      //final String    price  = Double.valueOf(fxMarketEvent.getPrice ());
      //final String    shares = Long.valueOf  (fxMarketEvent.getShares());

        final ProcessorReceiveDTO trade = new ProcessorReceiveDTO(id, stock, time, price, shares);

        log.info("[ProcessorImpl] process() Converting (" + fxMarketEvent + ") into (" + trade + ")");

        return trade;
    }
}
