package com.assignment.schedulerdemo.entity;

import javax.persistence.*;

@Entity
public class Cron {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cron_expression")
    private String cronExpression;

    public Cron(String cronExpression) {

        this.cronExpression = cronExpression;
    }

    public Cron(Long id, String cronExpression) {
        this.id = id;
        this.cronExpression = cronExpression;
    }

    public Cron() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
