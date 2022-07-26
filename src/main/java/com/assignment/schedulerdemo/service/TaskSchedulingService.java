package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.config.SchedulerConfiguration;
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

    @Autowired
    SchedulerConfiguration schedulerConfiguration;


    public void scheduleATask(String jobId, String taskId, Runnable tasklet, String cronExpression) {
        Map<String, ScheduledFuture<?>> taskMap;
        if (schedulerConfiguration.getJobsMap().get(jobId) == null)
            schedulerConfiguration.getJobsMap().put(jobId, new HashMap<>());
        taskMap = schedulerConfiguration.getJobsMap().get(jobId);
        System.out.println("Scheduling task with job id: " + jobId + " and task ID: " + taskId + " and cron expression: " + cronExpression);
        if (taskMap.containsKey(jobId + "." + taskId) && taskMap.get(jobId + "." + taskId) != null) {


            System.out.println(jobId + "." + taskId + "task already scheduled......");
            return;
        }
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        taskMap.put(jobId + "." + taskId, scheduledTask);
    }

    public void removeScheduledTask(String jobId, String taskId) {
        Map<String, ScheduledFuture<?>> taskMap = schedulerConfiguration.getJobsMap().get(jobId);
        ScheduledFuture<?> scheduledTask = taskMap.get(jobId + "." + taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            taskMap.put(jobId + "." + taskId, null);
        }
    }

    public void removeScheduledTasks(String jobId) {
        Map<String, ScheduledFuture<?>> taskMap = schedulerConfiguration.getJobsMap().get(jobId);
        ScheduledFuture<?> scheduledTask;

        for (Map.Entry<String, ScheduledFuture<?>> mapItr : taskMap.entrySet()) {
            scheduledTask = mapItr.getValue();
            if (scheduledTask != null) {
                scheduledTask.cancel(true);
                taskMap.put(mapItr.getKey(), null);
            }
        }

    }


}
