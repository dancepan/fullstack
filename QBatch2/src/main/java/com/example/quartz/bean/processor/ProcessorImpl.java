package com.example.quartz.bean.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.quartz.model.ReaderReturnDTO;
import com.example.quartz.model.ProcessorReceiveDTO;

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
        final String    stock  =                fxMarketEvent.getStock () ;
        final String    time   =                fxMarketEvent.getTime  () ;
        final double    price  = Double.valueOf(fxMarketEvent.getPrice ());
        final long      shares = Long.valueOf  (fxMarketEvent.getShares());

        final ProcessorReceiveDTO  trade  = new ProcessorReceiveDTO(stock, time, price, shares);

        log.info("[ProcessorImpl] process() Converting (" + fxMarketEvent + ") into (" + trade + ")");

        return trade;
    }
}
