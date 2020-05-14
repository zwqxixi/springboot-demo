package com.itstudent.springbootdemo.aop;

import com.itstudent.springbootdemo.service.ZkLockService;
import com.itstudent.springbootdemo.service.impl.ZkLockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: JDKProxy
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/3/19 16:26
 * @Description: TODO
 */
@Slf4j
public class JDKProxy implements InvocationHandler {
    //需要代理的目标对象
    private Object target;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("前置处理");
        Object result = method.invoke(proxy, args);
        log.info("后置处理");
        return result;
    }

    //定义一个获取代理类对象的方法
    public Object getJDKProxy(Object targetObject){
        this.target=targetObject;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target
        .getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        JDKProxy jdkProxy = new JDKProxy();
        ZkLockService zkLockService = (ZkLockService)jdkProxy.getJDKProxy(new ZkLockServiceImpl());
        zkLockService.getLock();
    }
}
