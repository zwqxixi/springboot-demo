package com.itstudent.springbootdemo.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @className: MySocketClient0
 * @author: wenqin.zhao
 * @createDate: 2020/9/10 15:33
 * @description: TODO
 */
@Slf4j
public class MySocketClient0 {

    public static void send(String message){
        Socket socket = null;
        try {
            socket = new Socket("localhost",8000);
            //获取输出流用于客户端向服务端发送数据
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(message);
            log.info("client0 send data:{}",message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        send("client0 ==== message");
    }
}
