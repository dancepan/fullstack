package com.example.quartz.config;

import java.util.List;

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

import com.example.quartz.model.ReaderReturnDTO;
import com.example.quartz.model.entity.BatchTarget;
import com.example.quartz.bean.listener.JobListenerExt;
import com.example.quartz.bean.processor.ProcessorImpl;
import com.example.quartz.bean.reader.ReaderExt;
import com.example.quartz.bean.reader.ReaderImpl;
import com.example.quartz.bean.writer.WriterImpl;
import com.example.quartz.bean.writer.WriterDBImpl;
import com.example.quartz.model.BizVO;
import com.example.quartz.model.ProcessorReceiveDTO;

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
    public BizVO fxMarketPricesStore()
    {
        return new BizVO();
    }

    public WriterDBImpl setupOverallWriter() {
        return new WriterDBImpl();
    }
    
    // FxMarketEventReader (Reader)
    @Bean
    public ReaderExt readerBean()
    {
        return new ReaderExt();
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
    public JobExecutionListener jobListen()
    {
        return new JobListenerExt();
    }

    // Configure job step
    @Bean
    public Job jobBean()
    {
        return jobBuilderFactory.get("MarketEventETLJob")          // Share Quartz Configuration
                                .incrementer(new RunIdIncrementer())  // Automatically parameter increase
                              //.listener   (jobListen())    // Must be Bean
                                .flow       (stepBean())
                                .end()
                                .build();
    }

    @Bean
    public Step stepBean()
    {
        // The job is thus scheduled to run every 2 minute. In fact it should
        // be successful on the first attempt, so the second and subsequent
        // attempts should through a JobInstanceAlreadyCompleteException, so you have to set allowStartIfComplete to true
        return stepBuilderFactory.get("MarketEventETLStep")
                                 .allowStartIfComplete(true)  // allows step rerunnig if there is job that success
                                 .<ReaderReturnDTO, ProcessorReceiveDTO> chunk(1000)  // First:Reader return type. Second:Writer receive type
                                 .reader   (    readerBean   ())
                               //.reader   (new ReaderImpl   ())
                                 .processor(    processorBean())
                               //.writer   (    writerBean   ())
                                 .writer   (new WriterDBImpl ())
                                 .build();
    }
}
