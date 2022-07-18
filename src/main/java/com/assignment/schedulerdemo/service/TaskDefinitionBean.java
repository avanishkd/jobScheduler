package com.assignment.schedulerdemo.service;

import com.assignment.schedulerdemo.entity.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskDefinitionBean implements Runnable {

    private Task task;

    @Override
    public void run() {
        System.out.println("Running Task: " + task.getId());
        System.out.println("Running action: " + task.getActionType());
        System.out.println("With Data: " + task.getData());
    }

    public Task getTaskDefinition() {
        return task;
    }

    public void setTaskDefinition(Task task) {
        this.task = task;
    }
}