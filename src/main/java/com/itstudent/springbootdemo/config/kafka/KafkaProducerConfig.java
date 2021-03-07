package com.itstudent.springbootdemo.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/4 9:52
 * @Description: kafka消息生成者配置
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String KafkaBootstrapServer;


    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBootstrapServer);
        //设置消息发送acks
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        //设置生产者内存缓冲去大小(kafka发送数据的时候会根据路由策略选择具体的partition 此时会导致send()方法阻塞)
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 4096);
        //设置生产者发送数据重试次数(如果生产者收到服务器发送失败的消息，则会发起重试,间隔100s);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //如果多个消息写入同一个分区,生产者将会将他们放入同一个批次(同一个批次可以使用的内存大小)；
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        //设置生产者发送同一批次消息前，等待消息加入的时间(提升吞吐量)
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }
}
