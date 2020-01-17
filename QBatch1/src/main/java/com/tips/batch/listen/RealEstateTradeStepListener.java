package com.tips.batch.listen;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class RealEstateTradeStepListener implements StepExecutionListener {
    
    Logger logger = LoggerFactory.getLogger(RealEstateTradeStepListener.class);
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String startTime = sf.format(stepExecution.getStartTime());

        logger.info("[" + startTime + "] " + stepExecution.getStepName() + " 시작...");
    }
 
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info(stepExecution.getStepName() + " 완료...");
        return ExitStatus.COMPLETED;
    }
}
