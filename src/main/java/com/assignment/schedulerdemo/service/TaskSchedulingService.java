package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.respository.TaskDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private TaskDefinitionRepository taskDefinitionRepository;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(String jobId, String taskId, Runnable tasklet, String cronExpression) {
        System.out.println("Scheduling task with job id: " + jobId + " and task ID: " + taskId + " and cron expression: " + cronExpression);
        if(jobsMap.containsKey(jobId+"."+taskId))
        {
            System.out.println(jobId+"."+taskId+  "task already scheduled......");
            return;
        }
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId+"."+taskId, scheduledTask);
    }

    public void removeScheduledTask(String jobIdDotTaskId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobIdDotTaskId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobIdDotTaskId, null);
//            taskDefinitionRepository.deleteById(jobIdDotTaskId);
        }
    }
}
