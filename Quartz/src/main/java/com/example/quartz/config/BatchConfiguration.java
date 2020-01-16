package com.example.quartz.config;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.example.quartz.etlprocess.FxMarketEvent;
import com.example.quartz.etlprocess.FxMarketPricesStore;
import com.example.quartz.etlprocess.Trade;
import com.example.quartz.step.ProcessorImpl;
import com.example.quartz.step.ReaderImpl;
import com.example.quartz.step.WriterImpl;
import com.example.quartz.step.WriterListener;

/**
 * 
 * @author ashraf
 */
@Configuration
@EnableBatchProcessing
@Import({QuartzConfiguration.class})
public class BatchConfiguration
{
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FxMarketPricesStore fxMarketPricesStore()
    {
        return new FxMarketPricesStore();
    }

    // FxMarketEventReader (Reader)
    @Bean
    public ReaderImpl readerBean()
    {
        return new ReaderImpl();
    }

    // FxMarketEventProcessor (Processor)
    @Bean
    public ProcessorImpl processorBean()
    {
        return new ProcessorImpl();
    }

    // StockPriceAggregator (Writer)
    @Bean
    public WriterImpl writerBean()
    {
        return new WriterImpl();
    }

    // JobCompletionNotificationListener (File loader)
    @Bean
    public JobExecutionListener writerListenerBean()
    {
        return new WriterListener();
    }

    // Configure job step
    @Bean
    public Job jobBean()
    {
        return jobBuilderFactory.get("fxmarket_prices_etl_job")
                                .incrementer(new RunIdIncrementer())
                                .listener(writerListenerBean())
                                .flow(stepBean())
                                .end()
                                .build();
    }

    @Bean
    public Step stepBean()
    {
        // The job is thus scheduled to run every 2 minute. In fact it should
        // be successful on the first attempt, so the second and subsequent
        // attempts should through a JobInstanceAlreadyCompleteException, so you have to set allowStartIfComplete to true
        return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load")
                                 .allowStartIfComplete(true)
                                 .<FxMarketEvent, Trade> chunk(10000)
                                 .reader   (readerBean   ())
                                 .processor(processorBean())
                                 .writer   (writerBean   ())
                                 .build();
    }
    
    // 새로 추가할 필요가..
//    @Bean public JobFactory jobFactory(AutowireCapableBeanFactory beanFactory) 
//    {
//    	return new SpringBeanJobFactory()
//    	{ 
//    		@Override protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception 
//    		{ 
//    			Object job = super.createJobInstance(bundle); 
//    			beanFactory.autowireBean(job); 
//    			return job; 
//    		} 
//    	}; 
//    }
}
