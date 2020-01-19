package com.tips.qbatch1.config;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.tips.qbatch1.job.JobCompletionListener;
import com.tips.qbatch1.listen.RealEstateTradeStepListener;
import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.model.json.ReaderItemJson;
import com.tips.qbatch1.step.Processor;
import com.tips.qbatch1.step.ReaderLocalApi;
import com.tips.qbatch1.step.Writer;

@Configuration
public class BatchConfig
{
	public static final int CHUNK_AND_PAGE_SIZE = 100;
	
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    RealEstateTradeStepListener realEstateTradeStepListener;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    

    @StepScope
    ItemReader<List<ReaderItemJson>> readerLocalApi() {
        return new ReaderLocalApi(restTemplate());
    }
    

    @StepScope
    ItemProcessor<List<ReaderItemJson>, List<TableOne>> processor() {
        return new Processor();
    }
    

    @StepScope
    ItemWriter<List<TableOne>> writer() {
        return new Writer();
    }
    
    
    @Bean(name = "realEstateTradeJob")
    public Job realEstateTradeJob(@Qualifier("realEstateAptTradeStep") Step orderStep1)
    {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(CHUNK_AND_PAGE_SIZE);
        threadPoolTaskExecutor.afterPropertiesSet();
        
    	Flow splitFlow = new FlowBuilder<Flow>("realEstateTradeStepSplitFlow")
    			.split(threadPoolTaskExecutor)
    			.add(
    				new FlowBuilder<Flow>("realEstateAptTradeStepFlow").start(orderStep1).build()
                ).build();
    	
        return jobBuilderFactory.get("[Batch] JobName1")
                                //.incrementer(new RunIdIncrementer())
                                //.listener(listener())
        		                 .start(splitFlow)
                                .end()
                                .build();
    }
    
    @Bean
    public Step realEstateAptTradeStep(JpaTransactionManager transactionManager
            , ItemReader<List<ReaderItemJson>> readerLocalApi
            , ItemProcessor<List<ReaderItemJson>, List<TableOne>> processor
            , ItemWriter<List<TableOne>> writer)
    {
        return stepBuilderFactory.get("[Batch] StepName1")
                               //.<String, String> chunk(1)
                                 .<List<ReaderItemJson>, List<TableOne>>chunk(1)
                               //.reader   (new ReaderLocApi())
                               //.reader   (new ReaderPubApi())
                                 .reader   (readerLocalApi)
                                 .processor(processor)
                                 .writer   (writer)
                                 .listener(realEstateTradeStepListener)
                                 .build();
    }


}
