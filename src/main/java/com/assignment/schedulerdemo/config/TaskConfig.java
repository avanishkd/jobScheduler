package com.assignment.schedulerdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Configuration
@AutoConfigureOrder(2)
public class TaskConfig {

    final static Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    @Scheduled(cron = "#{@getCronExpression5}")
    public void scheduleTasksCronFromDatabaseExpression5() {
        logger.info("scheduleTasksCronFromDatabaseExpression5 executed at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "#{@getCronExpression4}")
    public void scheduleTasksCronFromDatabaseExpression4() {
        logger.info("scheduleTasksCronFromDatabaseExpression4 executed at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "#{@getCronExpression3}")
    public void scheduleTasksCronFromDatabaseExpression3() {
        logger.info("scheduleTasksCronFromDatabaseExpression3 executed at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "#{@getCronExpression2}")
    public void scheduleTasksCronFromDatabaseExpression2() {
        logger.info("scheduleTasksCronFromDatabaseExpression2 executed at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "#{@getCronExpression1}")
    public void scheduleTasksCronFromDatabaseExpression1() {
        logger.info("scheduleTasksCronFromDatabaseExpression1 executed at {}", LocalDateTime.now());
    }
}
