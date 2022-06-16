package com.assignment.schedulerdemo.controller;

import com.assignment.schedulerdemo.config.TaskConfig;
import com.assignment.schedulerdemo.entity.Cron;
import com.assignment.schedulerdemo.respository.CronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cron")
public class CronController {

    @Autowired
    CronRepository cronRepo;

    @Autowired
    TaskConfig taskConfig;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Cron createCron(@RequestBody Cron cron) {
        Cron cronResponse = cronRepo.save(cron);
        taskConfig.scheduleTasksCronFromDatabaseExpression5();
        taskConfig.scheduleTasksCronFromDatabaseExpression4();
        taskConfig.scheduleTasksCronFromDatabaseExpression3();
        taskConfig.scheduleTasksCronFromDatabaseExpression2();
        taskConfig.scheduleTasksCronFromDatabaseExpression1();
        return cronResponse;


    }

}
