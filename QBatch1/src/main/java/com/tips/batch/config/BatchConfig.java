package com.tips.batch.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tips.batch.entity.FineDust;
import com.tips.batch.job.JobCompletionListener;
import com.tips.batch.step.Processor;
import com.tips.batch.step.ReaderLocApi;
import com.tips.batch.step.ReaderPubApi;
import com.tips.batch.step.ReaderPubApiMock;
import com.tips.batch.step.Writer;

@Configuration
public class BatchConfig
{
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob()
    {
        return jobBuilderFactory.get("[Batch] JobName1")
                                .incrementer(new RunIdIncrementer())
                                .listener(listener())
                                .flow(orderStep1())
                                .end()
                                .build();
    }

    @Bean
    public Step orderStep1()
    {
        return stepBuilderFactory.get("[Batch] StepName1")
                                 //.<String, String> chunk(1)
                                 .<List<FineDust>, List<FineDust>>chunk(1)
                               //.reader   (new ReaderLocApi())
                               //.reader   (new ReaderPubApi())
                                 .reader   (new ReaderPubApiMock())
                               //.processor(new Processor())
                                 .writer   (new Writer()   )
                                 .build();
    }

    @Bean
    public JobExecutionListener listener()
    {
        return new JobCompletionListener();
    }
}
