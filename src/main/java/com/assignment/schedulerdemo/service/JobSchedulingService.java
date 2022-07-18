package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.config.SchedulerConfiguration;
import com.assignment.schedulerdemo.respository.JobDefinitionRepository;
import com.assignment.schedulerdemo.respository.TaskDefinitionRepository;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;

@Service
public class JobSchedulingService {

    @Autowired
    SchedulerConfiguration schedulerConfiguration;

    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    @Autowired
    JobDefinitionRepository jobDefinitionRepository;

    public void scheduleAJob(JobDefinitionBean jobBean) {
        try {

            jobDefinitionRepository.save(jobBean.getJob());
            // specify the job' s details..
            JobDetail job = JobBuilder.newJob(JobDefinitionBean.class)
                    .withIdentity(jobBean.getJob().getJobId().toString(),jobBean.getJob().getJobGroupName())
                    .build();

            // specify the running period of the job
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobBean.getJob().getTriggerName(), jobBean.getJob().getTriggerGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobBean.getJob().getJobCronExpression()))
                    .forJob(jobBean.getJob().getJobId().toString(), jobBean.getJob().getJobGroupName())
                    .build();

            //schedule the job
           /* SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();*/
            Scheduler sch = schedulerConfiguration.getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);


        } catch (SchedulerException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void removeScheduledJob(String name, String group) throws SchedulerException {
        Scheduler sch = schedulerConfiguration.getScheduler();
        sch.deleteJob(JobKey.jobKey(name,group));
    }
}
