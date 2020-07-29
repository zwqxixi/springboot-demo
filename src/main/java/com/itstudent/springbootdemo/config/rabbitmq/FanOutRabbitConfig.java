package com.itstudent.springbootdemo.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/6 16:42
 * @Description: FanOut交换机类型(广播模式)
 */
@Configuration
public class FanOutRabbitConfig {

    //队列
    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA");
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB");
    }

    @Bean
    public Queue fanoutQueueC(){
        return new Queue("fanoutQueueC");
    }

    //交换机
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //绑定
    @Bean
    Binding bindingExchangeA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }

}
