package com.itstudent.springbootdemo.controller;

import com.itstudent.springbootdemo.model.User;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 15:14
 * @Description: rabbitmq测试接口
 */
@RestController
@EnableRabbit
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


     /**
      *
      * @Description: Rabbitmq Direct交换机测试
      * @param userId
      * @Return:
      * @Auther: wenqin.zhao
      * @CreateDate: 2019/11/6 15:28
      */
    @GetMapping("/sendDirect")
    private String sendDirect(Integer userId){
        User user=new User(userId,"admin","admin");
        rabbitTemplate.convertAndSend("CalonDirectExchange","CalonDirectRouting",user);
        return "OK,sendDirect:" + user;
    }

    @GetMapping("/sendTopicFirst")
    private String sendTopicFirst(Integer userId){
        User user=new User(userId,"admin","admin");
        rabbitTemplate.convertAndSend("topicExchange","topic.first",user);
        return "OK,sendTopicFirst:" + user;
    }

    @GetMapping("/sendTopicSecond")
    private String sendTopicSecond(Integer userId){
        User user=new User(userId,"admin","admin");
        rabbitTemplate.convertAndSend("topicExchange","topic.second",user);
        return "OK,sendTopicSecond:" + user;
    }

    @GetMapping("/sendFanout")
    private String sendFanout(Integer userId){
        User user=new User(userId,"admin","admin");
        rabbitTemplate.convertAndSend("fanoutExchange",null,user);
        return "OK,sendFanout:" + user;
    }

}
