package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.entity.JobEntity;
import com.assignment.schedulerdemo.entity.Task;
import com.assignment.schedulerdemo.respository.JobDefinitionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Set;

@Service
public class JobDefinitionBean implements Job {

    private JobEntity jobEntity;

    @Autowired
    JobDefinitionRepository jobDefinitionRepository;

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String taskJSONString=jobExecutionContext.getJobDetail().getJobDataMap().getString("tasks");
        Task[] taskJsonToArray;
        try {
             taskJsonToArray = objectMapper.readValue(taskJSONString, Task[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Running job: " + jobExecutionContext.getJobDetail());
//        TaskSchedulingService taskSchedulingService=new TaskSchedulingService();

        for (Task taskItr : taskJsonToArray) {
            TaskDefinitionBean taskDefinitionBean = new TaskDefinitionBean();
            taskDefinitionBean.setTaskDefinition(taskItr);
            taskSchedulingService.scheduleATask(jobExecutionContext.getJobDetail().getKey().getName().toString(), taskItr.getId(), taskDefinitionBean, taskItr.getCronExpression());

        }


    }

    public JobEntity getJob() {
        return jobEntity;
    }

    public void setJob(JobEntity jobEntity) {
        this.jobEntity = jobEntity;
    }


}
