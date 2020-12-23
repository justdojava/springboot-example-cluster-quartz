package com.example.cluster.quartz.config;

import com.example.cluster.quartz.listener.SimpleJobListener;
import com.example.cluster.quartz.listener.SimpleSchedulerListener;
import com.example.cluster.quartz.listener.SimpleTriggerListener;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author panzhi
 * @Description
 * @since 2020-12-09
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private QuartzJobFactory jobFactory;

    @Autowired
    private SimpleSchedulerListener simpleSchedulerListener;

    @Autowired
    private SimpleJobListener simpleJobListener;

    @Autowired
    private SimpleTriggerListener simpleTriggerListener;


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(propertiesFactoryBean.getObject());
        //使用数据源，自定义数据源
//        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);//支持在JOB实例中注入其他的业务对象
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        factory.setWaitForJobsToCompleteOnShutdown(true);//这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
        factory.setOverwriteExistingJobs(false);//是否覆盖己存在的Job
        factory.setStartupDelay(10);//QuartzScheduler 延时启动，应用启动完后 QuartzScheduler 再启动

        return factory;
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        //全局添加监听器
        //添加SchedulerListener监听器
        scheduler.getListenerManager().addSchedulerListener(simpleSchedulerListener);

        // 添加JobListener, 支持带条件匹配监听器
        scheduler.getListenerManager().addJobListener(simpleJobListener, KeyMatcher.keyEquals(JobKey.jobKey("myJob", "myGroup")));

        // 添加triggerListener，设置全局监听
        scheduler.getListenerManager().addTriggerListener(simpleTriggerListener, EverythingMatcher.allTriggers());
        return scheduler;
    }
}
