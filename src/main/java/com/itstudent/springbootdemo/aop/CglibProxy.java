package com.itstudent.springbootdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibProxy
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/3/19 16:44
 * @Description: TODO
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("前置处理");
        Object result = method.invoke(o, objects);
        log.info("前置处理");
        return result;
    }

    //定义获取代理类的方法
    public Object getCglibProxy(Object targetObject){
        this.target = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return o;
    }
}
