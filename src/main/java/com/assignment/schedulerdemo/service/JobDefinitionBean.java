package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.entity.JobEntity;
import com.assignment.schedulerdemo.entity.Task;
import com.assignment.schedulerdemo.respository.JobDefinitionRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println("Running job: " + jobExecutionContext.getJobDetail());
        JobEntity dbJobEntity = jobDefinitionRepository.findByJobId(Long.parseLong(jobExecutionContext.getJobDetail().getKey().getName()));
        Set<Task> taskList = dbJobEntity.getTasks();
        for (Task taskItr : taskList) {
            TaskDefinitionBean taskDefinitionBean = new TaskDefinitionBean();
            taskDefinitionBean.setTaskDefinition(taskItr);
            taskSchedulingService.scheduleATask(dbJobEntity.getJobId(), taskItr.getId(), taskDefinitionBean, taskItr.getCronExpression());
        }


    }

    public JobEntity getJob() {
        return jobEntity;
    }

    public void setJob(JobEntity jobEntity) {
        this.jobEntity = jobEntity;
    }


}
