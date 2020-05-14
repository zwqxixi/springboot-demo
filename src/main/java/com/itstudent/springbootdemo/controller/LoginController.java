/*
package com.itstudent.springbootdemo.controller;

import com.itstudent.springbootdemo.constant.BaseException;
import com.itstudent.springbootdemo.constant.ExceptionEnum;
import com.itstudent.springbootdemo.constant.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

*/
/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/9 16:24
 * @Description:
 *//*

@RestController
@RequestMapping("/api")
public class LoginController
{
    @Autowired
    private ItstudentMapper itstudentMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @RequestMapping("/user/login")
    public ResultVo<Itstudent> login(HttpServletRequest request, String account, String password)
    {
        Itstudent itstudent = itstudentMapper.selectUserByAuth(account, password);
        if (itstudent != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", itstudent.getStudentId());
            redisTemplate.opsForValue().set("loginUser:" + itstudent.getStudentId(), session.getId());
            redisTemplate.expire("loginUser:"+itstudent.getStudentId(),1800, TimeUnit.SECONDS);
            return new ResultVo(itstudent);
        } else {
            throw new BaseException(ExceptionEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
    }
    @RequestMapping("/getUser")
    public ResultVo getUser(){
        ResultVo resultVo=new ResultVo("haha");
        return  resultVo;
    }
}
*/
