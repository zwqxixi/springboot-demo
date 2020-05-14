package com.itstudent.springbootdemo.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String password;

    public User(Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}