package com.itstudent.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itstudent.springbootdemo.mapper.UserMapper;
import com.itstudent.springbootdemo.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public String getUser(String userName,String password){
    	User user=userMapper.selectUserByUserName(userName);
    	if(user!=null&&password.equals(user.getPassword())){
    		return "success";
    	}
    	return "failed";
    }
    
}
