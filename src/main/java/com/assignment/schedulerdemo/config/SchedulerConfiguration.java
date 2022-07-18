package com.assignment.schedulerdemo.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureOrder(1)
public class SchedulerConfiguration {
    final static Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Bean
    public Scheduler getScheduler() throws SchedulerException {

        SchedulerFactory schFactory = new StdSchedulerFactory();

        Scheduler sch = schFactory.getScheduler();

        return sch;
    }
}
