package com.itstudent.springbootdemo.task;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
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
public class TaskTest implements CommandLineRunner
{
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),new NamedThreadFactory("addThread_To_Queue"));
    @Override
    public void run(String... args) throws Exception {
        while (true) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(threadPoolExecutor.getQueue().size());
                }
            });
        }
    }
}
