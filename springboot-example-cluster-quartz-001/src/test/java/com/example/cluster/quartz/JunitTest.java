package com.example.cluster.quartz;

import com.alibaba.fastjson.JSON;
import com.example.cluster.quartz.entity.QuartzBean;
import com.example.cluster.quartz.entity.QuartzBeanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @author panzhi
 * @Description
 * @since 2020-12-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {
    @Autowired
    private QuartzBeanRepository quartzBeanRepository;

    @Test
    public void testInsert(){
        List<QuartzBean> result = quartzBeanRepository.findAll();
        System.out.println(JSON.toJSON(result));
    }
}
