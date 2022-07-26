package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.config.SchedulerConfiguration;
import com.assignment.schedulerdemo.respository.JobDefinitionRepository;
import com.assignment.schedulerdemo.respository.TaskDefinitionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            ObjectMapper objectMapper = new ObjectMapper();
            //Set pretty printing of json
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String taskArrayToJson = objectMapper.writeValueAsString(jobBean.getJob().getTasks());

            // specify the job' s details..
            JobDetail job = JobBuilder.newJob(JobDefinitionBean.class)
                    .withIdentity(jobBean.getJob().getJobId().toString(),jobBean.getJob().getJobGroupName())
                    .usingJobData("tasks",taskArrayToJson)
                    .build();

            // specify the running period of the job
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobBean.getJob().getTriggerName(), jobBean.getJob().getTriggerGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobBean.getJob().getJobCronExpression()))
                    .forJob(jobBean.getJob().getJobId().toString(), jobBean.getJob().getJobGroupName())
                    .build();


            Scheduler sch = schedulerConfiguration.getQuartzScheduler().getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);


        } catch (SchedulerException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void removeScheduledJob(String name, String group) throws SchedulerException {
        Scheduler sch = schedulerConfiguration.getScheduler();
        sch.deleteJob(JobKey.jobKey(name,group));
    }
}
