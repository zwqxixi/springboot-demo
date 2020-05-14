package com.itstudent.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: WebSocketServer
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/17 14:58
 * @Description: WebSocketServer相当于一个webSocket协议的controller,
 * 直接@ServerEndpoint("/imserver/{userId}") 、@Component启用即可，然后在里面实现@OnOpen开启连接，@onClose关闭连接，@onMessage接收消息等方法。
 */
@ServerEndpoint("/imserver/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    public static StringRedisTemplate redisTemplate;

    /*public static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }*/

    /**
     * 创建webSocketMap,用来存放userId（用户）对应的webSocketServer
     */
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap();

    /**
     * 使用websocket封装的session进行信息推送
     */
    private Session session;

    private String userId;

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
//    private static AtomicInteger onlineCount = new AtomicInteger(0);
//    private static  volatile int onlineCount = 0;
    private static int onlineCount = 0;

    //开启连接
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        //redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        redisTemplate.opsForZSet().add("zSetValue", "A", 1);
        redisTemplate.opsForZSet().add("zSetValue", "B", 3);
        redisTemplate.opsForZSet().add("zSetValue", "C", 2);
        redisTemplate.opsForZSet().add("zSetValue", "D", 5);
        log.info("webSocket:{}", webSocketMap);
        this.session = session;
        this.userId = userId;
        Session oldSession = webSocketMap.get(userId);
        if (oldSession != null) {
            webSocketMap.remove(userId);
            onlineCount--;
            webSocketMap.put(userId, session);
        } else {
            webSocketMap.put(userId, session);
            onlineCount++;//在线数加1
            Set<String> zSetValue = redisTemplate.opsForZSet().rangeByScore("zSetValue", 1, 6);
            log.info("当前用户{}，当前在线人数为{}", userId, onlineCount);
            try {
                //session.getBasicRemote().sendText("连接成功");
                session.getAsyncRemote().sendText(JSONObject.toJSONString(zSetValue));
            } catch (Exception e) {
                log.error("webSocket连接失败");
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("客户端发送消息,message:{}", message);
        session.getAsyncRemote().sendText("hello");
    }

    //关闭webSocket连接
    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session) {

        //session.getBasicRemote().sendText("连接关闭");
        //session.close();
        webSocketMap.remove(userId, session);
        onlineCount--;

    }

   /* public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }*/
}
