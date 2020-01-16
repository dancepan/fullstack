package com.kingbbode.config;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/*
 * Created By Kingbbode
 * blog : http://kingbbode.github.io
 * github : http://github.com/kingbbode
 * 
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 * kingbbode                2017-08-02      
 */

import com.kingbbode.properties.QuartzProperties;

@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
@EnableBatchProcessing
public class BatchConfiguration {
    /**
     * JobRegistry �뿉 Job �쓣 �옄�룞�쑝濡� �벑濡앺�?湲� �쐞�븳 �꽕�젙.
     *
     * @param jobRegistry ths Spring Batch Job Registry
     * @return JobRegistry BeanPostProcessor
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
    
    /**
     * Quartz Schedule Job �뿉 �쓽議댁�? 二쇱?��
     * 
     * @param beanFactory application context beanFactory
     * @return the job factory
     */
    @Bean
    public JobFactory jobFactory(AutowireCapableBeanFactory beanFactory) {
        return new SpringBeanJobFactory(){
            @Override
            protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
                Object job = super.createJobInstance(bundle);
                beanFactory.autowireBean(job);
                return job;
            }
        };
    }
    
    /**
     * Scheduler �쟾泥�?�� ?���由?�븯�뒗 Manager.
     *
     * @param datasource Spring datasource
     * @param quartzProperties quartz config    
     * @return the scheduler factory bean
     * @throws Exception the exception
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource datasource, QuartzProperties quartzProperties, JobFactory jobFactory, Trigger[] registryTrigger) throws Exception {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setSchedulerName("SampleProject-0.0.1");
        //Register JobFactory
        factory.setJobFactory(jobFactory);
        //Graceful Shutdown �쓣 �쐞�븳 �꽕�젙�쑝濡� Job �씠 �셿?��?���? �븣源뚯�? Shutdown �쓣 ��湲고�?�뒗 �꽕�젙
        factory.setWaitForJobsToCompleteOnShutdown(true);
        //Job Detail �뜲�씠�꽣 Overwrite ��??���?
        factory.setOverwriteExistingJobs(true);
        //Register QuartzProperties
        factory.setQuartzProperties(quartzProperties.toProperties());
        //Schedule ?���由?���? Spring Datasource �뿉 �쐞�엫
        factory.setDataSource(datasource);
        //Register Triggers
        factory.setTriggers(registryTrigger);

        return factory;
    }

    /**
     * Scheduler �뿉 Trigger ?���? �옄�룞�쑝濡� �벑濡앺�?湲� �쐞�븳 �꽕�젙.
     *
     * @return the trigger [ ]
     */
    @Bean
    public Trigger[] registryTrigger(List<CronTriggerFactoryBean> cronTriggerFactoryBeanList) {
        return cronTriggerFactoryBeanList.stream().map(CronTriggerFactoryBean::getObject).toArray(Trigger[]::new);
    }

    /**
     * Spring Framework �쓽 Shutdown Hook �꽕�젙.
     * Quartz �쓽 Shutdown �룞�옉�쓣 �쐞�엫諛쏆�? Graceful Shutdown �쓣 蹂댁?��.
     * Quartz �쓽 �옄泥� Shutdown Plugin �쓣 �궗�슜�븯硫� Spring �쓽 Datasource 媛� ?��?���? Close �릺誘�濡�,
     * Spring �뿉寃� Shutdown �룞�옉�쓣 �쐞�엫�븯�뿬, �긽�쐞�뿉�꽌 ?�⑦?��濡�.
     *
     * @param schedulerFactoryBean quartz schedulerFactoryBean.
     * @return SmartLifecycle
     */
    
    @Bean
    public SmartLifecycle gracefulShutdownHookForQuartz(SchedulerFactoryBean schedulerFactoryBean) {
        return new SmartLifecycle() {
            private boolean isRunning = false;
            private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
            @Override
            public boolean isAutoStartup() {
                return true;
            }

            @Override
            public void stop(Runnable callback) {
                stop();
                logger.info("Spring container is shutting down.");
                callback.run();
            }

            @Override
            public void start() {
                logger.info("Quartz Graceful Shutdown Hook started.");
                isRunning = true;
            }

            @Override
            public void stop() {
                isRunning = false;
                try {
                    logger.info("Quartz Graceful Shutdown... ");
                    schedulerFactoryBean.destroy();
                } catch (SchedulerException e) {
                    try {
                        logger.info(
                                "Error shutting down Quartz: " + e.getMessage(), e);
                        schedulerFactoryBean.getScheduler().shutdown(false);
                    } catch (SchedulerException ex) {
                        logger.error("Unable to shutdown the Quartz scheduler.", ex);
                    }
                }
            }

            @Override
            public boolean isRunning() {
                return isRunning;
            }

            @Override
            public int getPhase() {
                return Integer.MAX_VALUE;
            }
        };
    }
}
