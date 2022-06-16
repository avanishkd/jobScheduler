package com.assignment.schedulerdemo.controller;

import com.assignment.schedulerdemo.entity.TaskDefinition;
import com.assignment.schedulerdemo.respository.TaskDefinitionRepository;
import com.assignment.schedulerdemo.service.TaskDefinitionBean;
import com.assignment.schedulerdemo.service.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/schedule")
public class JobSchedulingController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        taskDefinitionBean.setTaskDefinition(taskDefinition);
        taskSchedulingService.scheduleATask(taskDefinition.getId(), taskDefinitionBean, taskDefinition.getCronExpression());
        taskDefinitionRepository.save(taskDefinition);
    }

    @GetMapping(path="/remove/{jobid}")
    public void removeJob(@PathVariable String jobid) {
        taskSchedulingService.removeScheduledTask(jobid);
        taskDefinitionRepository.deleteById(jobid);
    }
}