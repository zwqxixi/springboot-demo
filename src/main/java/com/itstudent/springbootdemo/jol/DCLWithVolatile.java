package com.itstudent.springbootdemo.jol;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import org.openjdk.jol.info.ClassLayout;

/**
 * @className: DCLWithVolatile
 * @author: wenqin.zhao
 * @createDate: 2020/4/6 22:48
 * @description: TODO
 */
public class DCLWithVolatile {

    private int i = 1;

    public synchronized void add(){
        i++;
    }

    /*//单例模式：饥汉模式
    //上来就new一个构造方法
    private static final DCLWithVolatile instantce = new DCLWithVolatile();
    //设置构造方法为私有的
    private DCLWithVolatile(){};

    public DCLWithVolatile getInstance(){
        return instantce;
    }*/

    //懒汉模式 等线程需要的时候再new
    private static volatile DCLWithVolatile instance;

    private DCLWithVolatile(){};

    public static synchronized DCLWithVolatile getInstance(){
        if(instance == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new DCLWithVolatile();
        }
        return instance;
    }
     // Double Check Lock(DCL)
    /*public static DCLWithVolatile getInstance(){
        if(instance == null){
            synchronized (DCLWithVolatile.class){
                if(instance == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new DCLWithVolatile();
                }
            }
        }
        return instance;
    }*/



    public static void main(String[] args) {
        for (int i = 0; i< 100;i++){
            new Thread(()->{
                System.out.println(DCLWithVolatile.getInstance().hashCode());
            }).start();
        }


    }

}
