package com.itstudent.springbootdemo.listener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/9/21 16:04
 * @Description:
 */
@Component
public class KafkaListenerTest {

    /*@KafkaListener(topics = "test_topic")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }*/

}
