package com.assignment.schedulerdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    private String id;
    private String cronExpression;
    private String actionType;
    private String data;

    public Task() {
    }

    public Task(String id, String cronExpression, String actionType, String data) {
        this.id = id;
        this.cronExpression = cronExpression;
        this.actionType = actionType;
        this.data = data;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
