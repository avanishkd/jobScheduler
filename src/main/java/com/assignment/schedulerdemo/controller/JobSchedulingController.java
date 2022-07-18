package com.assignment.schedulerdemo.controller;

import com.assignment.schedulerdemo.entity.JobEntity;
import com.assignment.schedulerdemo.entity.Task;
import com.assignment.schedulerdemo.respository.TaskDefinitionRepository;
import com.assignment.schedulerdemo.service.JobDefinitionBean;
import com.assignment.schedulerdemo.service.JobSchedulingService;
import com.assignment.schedulerdemo.service.TaskDefinitionBean;
import com.assignment.schedulerdemo.service.TaskSchedulingService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/schedule")
public class JobSchedulingController {

    @Autowired
    private JobSchedulingService jobSchedulingService;

    @Autowired
    private JobDefinitionBean jobDefinitionBean;



    @PostMapping(path="/jobDef", consumes = "application/json", produces="application/json")
    public void scheduleAJob(@RequestBody JobEntity jobEntity) {
        jobDefinitionBean.setJob(jobEntity);
        jobSchedulingService.scheduleAJob(jobDefinitionBean);

    }

    @GetMapping(path="/remove")
    public void removeJob(@RequestParam String name,@RequestParam String group) throws SchedulerException {
        jobSchedulingService.removeScheduledJob(name,group);

    }
}