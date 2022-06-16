package com.assignment.schedulerdemo;

import com.assignment.schedulerdemo.entity.Cron;
import com.assignment.schedulerdemo.respository.CronRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SchedulerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerDemoApplication.class, args);
    }

  /*  @Bean
    CommandLineRunner init (CronRepository cronRepo){
        return args -> {
            List<String> expressions = Arrays.asList("0 23 22 15 * ?", "0 24 22 15 * ?");
            expressions.forEach(expression -> cronRepo.save(new Cron( expression)));
        };
    }*/
}
