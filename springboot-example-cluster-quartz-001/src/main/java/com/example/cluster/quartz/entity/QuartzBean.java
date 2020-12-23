package com.example.cluster.quartz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author panzhi
 * @Description
 * @since 2020-12-04
 */
@Entity
@Table(name = "tb_job_task")
public class QuartzBean {

    /**
     * 任务ID
     */
    @Id
    private String id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务执行类
     */
    @Column(name = "job_class")
    private String jobClass;

    /**
     * 任务调度时间表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 任务状态，0：启动，1：暂停，2：停用
     */
    @Column(name = "status")
    private Integer status;


    public String getId() {
        return id;
    }

    public QuartzBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public QuartzBean setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getJobClass() {
        return jobClass;
    }

    public QuartzBean setJobClass(String jobClass) {
        this.jobClass = jobClass;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public QuartzBean setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuartzBean setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
