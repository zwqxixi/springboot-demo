package com.itstudent.springbootdemo.service.impl;

import com.itstudent.springbootdemo.constant.SHA512Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/20 17:20
 * @Description:
 */
@Service
public class AuthCachService{

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String AUTH_TOKEN = "auth:token:";
    private static final String AUTH_USER = "auth:user:";
    private static final int TOKEN_EXPRIED_MINUTES = 30;
    private static final String SEPARATOR = ".";
    public String auth(String itstudentId,String password ,int clientId){
        String passInfo=new StringBuffer(clientId).append(SEPARATOR).append(itstudentId).append(SEPARATOR).append(password).toString();
        String token=new StringBuffer(clientId).append(SEPARATOR).append(SHA512Util.SHA256(passInfo)).toString();
        redisTemplate.opsForValue().set(AUTH_TOKEN+"token",itstudentId,TOKEN_EXPRIED_MINUTES, TimeUnit.SECONDS);
        return token;
    }

    public void exit(String token){
        if(StringUtils.isNotEmpty(token)){
            redisTemplate.delete(AUTH_TOKEN+"token");
        }
    }
}
