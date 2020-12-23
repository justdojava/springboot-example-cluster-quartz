package com.example.cluster.quartz.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author panzhi
 * @Description
 * @since 2020-12-04
 */

public interface QuartzBeanRepository extends JpaRepository<QuartzBean,String> {

    /**
     * 修改任务状态
     * @param id
     * @param status
     */
    @Modifying
    @Transactional
    @Query("update QuartzBean m set m.status = ?2 where m.id =?1")
    void updateState(String id, Integer status);

    /**
     * 修改任务调度时间
     * @param id
     * @param cronExpression
     */
    @Modifying
    @Transactional
    @Query("update QuartzBean m set m.cronExpression = ?2 where m.id =?1")
    void updateCron(String id, String cronExpression);
}
