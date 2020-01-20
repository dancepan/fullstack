package com.tips.qbatch5.oracle.adw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.oracle.adw.repository.jdbc", "com.oracle.adw.repository.jpa"})
@SpringBootApplication
public class ADWBatchJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ADWBatchJobApplication.class, args);
	}

}
