package com.tips.qbatch3.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.tips.qbatch3.properties.QuartzProperties;

/**
 * Created by YG-MAC on 2017. 10. 23..
 */
@Component
@EnableConfigurationProperties(QuartzProperties.class)
public class BatchNameProvider {

    @Autowired
    private QuartzProperties quartzProperties;

    
}
