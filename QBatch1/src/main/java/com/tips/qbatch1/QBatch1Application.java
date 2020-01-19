package com.tips.qbatch1;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableScheduling
@SpringBootApplication
@EnableBatchProcessing
public class QBatch1Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(QBatch1Application.class, args);
	}
}
