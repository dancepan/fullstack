package com.tips.qbatch6.custom.in;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tips.qbatch6.common.LoggingStudentProcessor;
import com.tips.qbatch6.common.LoggingStudentWriter;
import com.tips.qbatch6.student.StudentDTO;

/**
 * @author Petri Kainulainen
 */
@Configuration
public class InMemoryStudentJobConfig {

    @Bean
    ItemReader<StudentDTO> inMemoryStudentReader() {
        return new InMemoryStudentReader();
    }

    @Bean
    ItemProcessor<StudentDTO, StudentDTO> inMemoryStudentProcessor() {
        return new LoggingStudentProcessor();
    }

    @Bean
    ItemWriter<StudentDTO> inMemoryStudentWriter() {
        return new LoggingStudentWriter();
    }

    @Bean
    Step inMemoryStudentStep(ItemReader<StudentDTO> inMemoryStudentReader,
                             ItemProcessor<StudentDTO, StudentDTO> inMemoryStudentProcessor,
                             ItemWriter<StudentDTO> inMemoryStudentWriter,
                             StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("inMemoryStudentStep")
                .<StudentDTO, StudentDTO>chunk(1)
                .reader(inMemoryStudentReader)
                .processor(inMemoryStudentProcessor)
                .writer(inMemoryStudentWriter)
                .build();
    }

    @Bean
    Job inMemoryStudentJob(JobBuilderFactory jobBuilderFactory,
                           @Qualifier("inMemoryStudentStep") Step inMemoryStudentStep) {
        return jobBuilderFactory.get("inMemoryStudentJob")
                .incrementer(new RunIdIncrementer())
                .flow(inMemoryStudentStep)
                .end()
                .build();
    }
}
