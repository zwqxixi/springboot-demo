package com.itstudent.springbootdemo.task;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: TaskTest
 * @author: wenqin.zhao
 * @createDate: 2020/4/30 14:54
 * @description: TODO
 */
@Slf4j
@Component
public class TaskTest implements CommandLineRunner {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,6,60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10),new NamedThreadFactory("addThread_To_Queue"));

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    kafkaTemplate.send("test_topic","日志===========");
                }
            });
        }
    }
}
