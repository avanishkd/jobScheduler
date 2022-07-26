package com.assignment.schedulerdemo.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Configuration
@AutoConfigureOrder(1)
public class SchedulerConfiguration {
    final static Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Bean
    public Scheduler getScheduler() throws SchedulerException {

        SchedulerFactory schFactory = new StdSchedulerFactory();

        Scheduler sch = schFactory.getScheduler();

        return sch;
    }

    @Bean
    public Map<String, ScheduledFuture<?>> getTaskMap(){
        Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();
        return jobsMap;
    }

    @Bean
    public SchedulerFactoryBean getQuartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        return quartzScheduler;
    }

    @Bean
    public Map<String,Map<String, ScheduledFuture<?>>> getJobsMap(){
        Map<String,Map<String, ScheduledFuture<?>>> jobsMap = new HashMap<>();
        return jobsMap;
    }
}
