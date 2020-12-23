package com.example.cluster.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author panzhi
 * @Description
 * @since 2020-12-09
 */
public class TfCommandJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(TfCommandJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        try {
            System.out.println(context.getScheduler().getSchedulerInstanceId() + "--" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        } catch (SchedulerException e) {
            log.error("任务执行失败",e);
        }
    }
}
