package com.itstudent.springbootdemo.jol;

import org.openjdk.jol.info.ClassLayout;


/**
 * @className: SychronizedTest
 * @author: wenqin.zhao
 * @createDate: 2020/4/5 0:58
 * @description: TODO
 */
public class SychronizedTest {

    long m;

    public SychronizedTest(Long m) {
        this.m = m;
    }

    public static void main(String[] args) {
        //Object o = new Object();
        SychronizedTest sychronizedTest = new SychronizedTest(8L);
        String s = ClassLayout.parseInstance(sychronizedTest).toPrintable();
        //打印java对象的内存布局
        System.out.println(s);

        /*synchronized (sychronizedTest){
            String s1 = ClassLayout.parseInstance(sychronizedTest).toPrintable();
            //打印java对象的内存布局
            System.out.println(s1);
        }*/
        String s1 = ClassLayout.parseInstance(sychronizedTest).toPrintable();
        //打印java对象的内存布局
        System.out.println(s1);
    }
}
