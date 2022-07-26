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

    @Autowired
    TaskSchedulingService taskSchedulingService;


    @PostMapping(path = "/jobDef", consumes = "application/json", produces = "application/json")
    public void scheduleAJob(@RequestBody JobEntity jobEntity) {
        jobDefinitionBean.setJob(jobEntity);
        jobSchedulingService.scheduleAJob(jobDefinitionBean);

    }

    @GetMapping(path = "job/remove")
    public void removeJob(@RequestParam String name, @RequestParam String group) throws SchedulerException {
        jobSchedulingService.removeScheduledJob(name, group);

    }

    @GetMapping(path = "task/remove")
    public void removeTask(@RequestParam String jobId, @RequestParam String taskId) {
        taskSchedulingService.removeScheduledTask(jobId, taskId);
    }

    @GetMapping(path = "task/removeAll")
    public void removeAllTask(@RequestParam String jobId) {
        taskSchedulingService.removeScheduledTasks(jobId);
    }

    @GetMapping(path = "job/task/removeAll")
    public void removeAllJobWithTasks(@RequestParam String jobId, @RequestParam String group) {
        try {
            jobSchedulingService.removeScheduledJobWithTasks(jobId,group);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}