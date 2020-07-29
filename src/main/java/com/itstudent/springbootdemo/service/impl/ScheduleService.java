package com.itstudent.springbootdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @className: ScheduleService
 * @author: wenqin.zhao
 * @createDate: 2020/7/22 17:39
 * @description: TODO
 */
@Service
@Slf4j
public class ScheduleService {

    private static Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Scheduled(cron = "0 */1 * * * ?")
    public void printLog(){
        log.info("Service开始日志打印==========");
        log.info("Service日志打印结束==========");
    }
}
