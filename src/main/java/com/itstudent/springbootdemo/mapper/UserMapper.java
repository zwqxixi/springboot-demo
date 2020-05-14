package com.itstudent.springbootdemo.mapper;

import org.springframework.stereotype.Repository;

import com.itstudent.springbootdemo.model.User;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
   User selectUserByUserName(String userName);
}
