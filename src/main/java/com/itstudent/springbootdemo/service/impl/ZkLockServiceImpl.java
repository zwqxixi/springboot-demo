package com.itstudent.springbootdemo.service.impl;

import com.itstudent.springbootdemo.service.ZkLockService;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

public class ZkLockServiceImpl implements ZkLockService {

    //Zk连接地址
    private static final String CONNECTSTRING="127.0.0.1;2181";
    private static final String PATH="/lock";//临时节点名称
    private ZkClient zkClient=new ZkClient(CONNECTSTRING);
    private CountDownLatch countDownLatch=null;//使用信号量来做为监听器通知

    @Override
    public void getLock() {
        if(tryLock()){
            System.out.println("获取锁成功");
        }else{
            waitLock();//等待
            tryLock();
        }
    }

    @Override
    public void unLock() {
        if(zkClient!=null){
            zkClient.close();
            System.out.println("释放锁");
        }
    }
    //尝试去获取锁
    Boolean tryLock(){
        try{
            zkClient.createEphemeral(PATH);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    void waitLock(){
        //使用事件监听器
        IZkDataListener iZkDataListener=new IZkDataListener() {
            //当节点被删除
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                countDownLatch.countDown();//唤醒
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };
        //注册节点信息
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        //判断临时节点是否存在
        if(zkClient.exists(PATH)) {
            try {
                countDownLatch.await();
            } catch (Exception e) {

            }
        }
        zkClient.unsubscribeDataChanges(PATH,iZkDataListener);//删除事件通知
    }
}
