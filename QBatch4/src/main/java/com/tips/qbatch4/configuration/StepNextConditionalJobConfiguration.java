package com.tips.qbatch4.configuration;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextConditionalJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
                                .start(conditionalJobStep1())
                                .on("FAILED")                  // FAILED �� ���
                                .to(conditionalJobStep3())     // step3���� �̵��Ѵ�.
                                .on("*")                       // step3�� ��� ���� ���� 
                                .end()                         // step3���� �̵��ϸ� Flow�� �����Ѵ�.
                                .from(conditionalJobStep1())   // step1�κ���
                                .on("*")                       // FAILED �ܿ� ��� ���
                                .to(conditionalJobStep2())     // step2�� �̵��Ѵ�.
                                .next(conditionalJobStep3())   // step2�� ���� ����Ǹ� step3���� �̵��Ѵ�.
                                .on("*")                       // step3�� ��� ���� ���� 
                                .end()                         // step3���� �̵��ϸ� Flow�� �����Ѵ�.
                                .end()                         // Job ����
                                .build();
    }

    @Bean
    public Step conditionalJobStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step1");

                    /**
                        ExitStatus�� FAILED�� �����Ѵ�.
                                         �ش� status�� ���� flow�� ����ȴ�.
                    **/
                    //contribution.setExitStatus(ExitStatus.FAILED);

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalJobStep2() {
        return stepBuilderFactory.get("conditionalJobStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalJobStep3() {
        return stepBuilderFactory.get("conditionalJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step3");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}