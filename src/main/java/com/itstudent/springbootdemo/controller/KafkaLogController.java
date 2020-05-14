package com.itstudent.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/9/21 15:41
 * @Description:
 */
@RestController
public class KafkaLogController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/sendMessage")
    public String sendMsg(String msg){
        kafkaTemplate.send("test_topic",msg);
        return "success";
    }
}
