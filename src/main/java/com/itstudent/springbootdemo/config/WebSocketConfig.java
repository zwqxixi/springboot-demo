package com.itstudent.springbootdemo.config;
import com.itstudent.springbootdemo.controller.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName: WebSocketConfig
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/17 14:59
 * @Description: 开启websocket支持
 */

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate redisTemplate){
        WebSocketServer.redisTemplate = redisTemplate;
    }
}
