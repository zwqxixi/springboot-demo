package com.itstudent.springbootdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 15:23
 * @Description: Direct类型的交换机
 */
@Configuration
public class DirectRabbitConfig {
    //队列
    @Bean
    public Queue CalonDirectQueue() {
        return new Queue("CalonDirectQueue",true);
    }
    //Direct交换机
    @Bean
    DirectExchange CalonDirectExchange() {
        return new DirectExchange("CalonDirectExchange");
    }
    //绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(CalonDirectQueue()).to(CalonDirectExchange()).with("CalonDirectRouting");
    }
}
