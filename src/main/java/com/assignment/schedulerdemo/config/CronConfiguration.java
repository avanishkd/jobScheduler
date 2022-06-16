package com.assignment.schedulerdemo.config;

import com.assignment.schedulerdemo.entity.Cron;
import com.assignment.schedulerdemo.respository.CronRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@AutoConfigureOrder(1)
public class CronConfiguration {

    final static Logger logger = LoggerFactory.getLogger(CronConfiguration.class);
    @Autowired
    private CronRepository cronRepo;

    @Bean
    public Cron saveCron1() {

        //Schedule task for 16 of every month at 1234 hours
        return cronRepo.save(new Cron(1L, "0 37 12 16 * ?"));
    }

    @Bean
    public Cron saveCron2() {

        //Schedule task for 16 of every month at 1236 hours
        return cronRepo.save(new Cron(2L, "0 36 12 16 * ?"));
    }

    @Bean
    public Cron saveCron3() {

        //Schedule task for 16 of every month at 1235 hours
        return cronRepo.save(new Cron(3L, "0 35 12 16 * ?"));
    }

    @Bean
    public Cron saveCron4() {

        //Schedule task for 16 of every month at 1234 hours
        return cronRepo.save(new Cron(4L, "0 34 12 16 * ?"));
    }

    @Bean
    public Cron saveCron5() {

        //Schedule task for 16 of every month at 1233 hours
        return cronRepo.save(new Cron(5L, "0 33 12 16 * ?"));
    }

    @Bean
    @DependsOn("saveCron1")
    public String getCronExpression1() {


        return cronRepo.findById(1L).get().getCronExpression().toString();
    }

    @Bean
    @DependsOn("saveCron2")
    public String getCronExpression2() {

        return cronRepo.findById(2L).get().getCronExpression().toString();
    }

    @Bean
    @DependsOn("saveCron3")
    public String getCronExpression3() {

        return cronRepo.findById(3L).get().getCronExpression().toString();
    }

    @Bean
    @DependsOn("saveCron4")
    public String getCronExpression4() {

        return cronRepo.findById(4L).get().getCronExpression().toString();
    }

    @Bean
    @DependsOn("saveCron5")
    public String getCronExpression5() {

        return cronRepo.findById(5L).get().getCronExpression().toString();
    }


}
